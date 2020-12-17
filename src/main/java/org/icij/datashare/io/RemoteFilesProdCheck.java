package org.icij.datashare.io;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.junit.After;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;

public class RemoteFilesProdCheck {
    private static final String S3_DATASHARE_BUCKET_NAME = "datashare-nlp";
    private static final String S3_DATASHARE_ENDPOINT = "https://icij.org";
    private static final String S3_REGION = "us-east-1";
    private static final int READ_TIMEOUT_MS = 120 * 1000;
    private static final int CONNECTION_TIMEOUT_MS = 30 * 1000;
    AmazonS3 s3client = getDefault();

    @Test
    public void test_directory_listing() {
        ObjectListing objectListing = s3client.listObjects(S3_DATASHARE_BUCKET_NAME, "dist/test/dir1/");
        List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries().stream().filter(os -> os.getSize() != 0).collect(Collectors.toList());

        assertThat(s3ObjectSummaries.size()).isEqualTo(1);
        assertThat(s3ObjectSummaries.get(0).getKey()).contains("file1.txt");
    }

    public static AmazonS3 getDefault() {
        ClientConfiguration config = new ClientConfiguration();
        config.setConnectionTimeout(CONNECTION_TIMEOUT_MS);
        config.setSocketTimeout(READ_TIMEOUT_MS);
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(S3_DATASHARE_ENDPOINT, S3_REGION))
                .withClientConfiguration(config).build();
    }

    @After public void tearDown() { s3client.shutdown();}
}
