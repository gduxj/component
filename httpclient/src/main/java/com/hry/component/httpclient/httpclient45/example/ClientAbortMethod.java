package com.hry.component.httpclient.httpclient45.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * This example demonstrates how to abort an HTTP method before its normal completion.
 */
public class ClientAbortMethod {

    public final static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://httpbin.org/get");
        //	HttpGet httpget = new HttpGet("http://localhost:8080/simple");

            System.out.println("Executing request " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                // Do not feel like reading the response body
                // Call abort on the request object
                httpget.abort();
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}