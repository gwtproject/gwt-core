package org.gwtproject.junit;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.junit.JUnitShell;
import com.google.gwt.junit.RunStyle;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class RunStyleWebDriver extends RunStyle {

    private List<RemoteWebDriver> browsers = new ArrayList<>();
    private Thread keepalive;

    public RunStyleWebDriver(JUnitShell shell) {
        super(shell);
    }

    @Override
    public int initialize(String args) {
        if (args == null || args.length() == 0) {
            getLogger().log(TreeLogger.ERROR, "WebDriver runstyle requires a parameter of the form protocol://hostname:port?browser1[,browser2]");
            return -1;
        }
        String[] parts = args.split("\\?");
        URL remoteAddress = null;
        try {
            remoteAddress = new URL(parts[0] + "/wd/hub");
        } catch (MalformedURLException e) {
            getLogger().log(TreeLogger.ERROR, e.getMessage(), e);
            return -1;
        }

        // build each driver based on parts[1].split(",")
        String[] browserNames = parts[1].split(",");

        for (String browserName : browserNames) {
            DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);

            try {
                RemoteWebDriver wd = new RemoteWebDriver(remoteAddress, capabilities);
                browsers.add(wd);
            } catch (Exception exception) {
                getLogger().log(TreeLogger.ERROR, "Failed to find desired browser", exception);
                return -1;
            }
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (keepalive != null) {
                keepalive.interrupt();
            }
            for (RemoteWebDriver browser : browsers) {
                try {
                    browser.quit();
                } catch (Exception ignored) {
                    // ignore, we're shutting down, continue shutting down others
                }
            }
        }));
        return browsers.size();
    }

    @Override
    public void launchModule(String moduleName) throws UnableToCompleteException {
        // since WebDriver.get is blocking, start a keepalive thread first
        keepalive = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                for (RemoteWebDriver browser : browsers) {
                    browser.getTitle();// as in RunStyleSelenium, simple way to poll the browser
                }
            }
        });
        keepalive.setDaemon(true);
        keepalive.start();
        for (RemoteWebDriver browser : browsers) {
            browser.get(shell.getModuleUrl(moduleName));
        }
    }

    /**
     * Work-around until GWT's JUnitShell handles IPv6 addresses correctly.
     * https://groups.google.com/d/msg/google-web-toolkit/jLGhwUrKVRY/eQaDO6EUqdYJ
     */
    public String getLocalHostName() {
        String host = System.getProperty("webdriver.test.host");
        if (host != null) {
            return host;
        }
        InetAddress a;
        try {
            a = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unable to determine my ip address", e);
        }
        if (a instanceof Inet6Address) {
            return "[" + a.getHostAddress() + "]";
        } else {
            return a.getHostAddress();
        }
    }
}
