# s3-directory-listing-status

Small utility to check if S3 directory listing is working properly with a CDN backed with S3. It could be used for monitoring.

## build

With maven : 

```
$ mvn package
```

## run

It builds an auto-executable jar that can be run like this :

```
$ java -jar target/s3-directory-listing-status-1.0-jar-with-dependencies.jar
```

It checks the CDN for datashare NLP libraries. You can change the following parameters with environment variables :

* `CHECK_S3_BUCKET_NAME` : S3 bucket name
* `CHECK_S3_ENDPOINT` : S3 endpoint
* `CHECK_S3_REGION` : S3 region
* `CHECK_S3_PATH` : directory path to check (there must be a file called `file1.txt` in it)


