package com.example.hanhb.aws;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.models.nosql.UsersDO;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class MainActivity extends AppCompatActivity {

    // Declare a DynamoDBMapper object
    DynamoDBMapper dynamoDBMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AWSMobileClient.getInstance().initialize(this).execute();

        // Instantiate a AmazonDynamoDBMapperClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();



    }

    public void createNews() {
        final UsersDO userItem = new UsersDO();

        userItem.setAge(27d);
        userItem.setName("Hyobyung Han");
        userItem.setUserId("?");

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(userItem);
                // Item saved
            }
        }).start();
    }


}
