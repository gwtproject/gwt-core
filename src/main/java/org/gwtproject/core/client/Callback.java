package org.gwtproject.core.client;

/**
 * A callback for any asynchronous call that can result in success or failure.
 *
 * @param <T> The type returned on success
 * @param <F> The type returned on failure
 */
public interface Callback<T, F> {

    /**
     * Called when an asynchronous call fails to complete normally.
     *
     * @param reason failure encountered
     */
    void onFailure(F reason);

    /**
     * Called when an asynchronous call completes successfully.
     *
     * @param result the value returned
     */
    void onSuccess(T result);
}
