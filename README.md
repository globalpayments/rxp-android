# Realex Payments Android Library
You can find more information on how to use this SDK and sign up for a free Realex Payments sandbox account at https://developer.realexpayments.com

## Requirements

- Android 4.4+
- Android SDK 19 or later

## HPP Library Installation

### Gradle users
Add this dependency to your project's build file:
```
compile "com.realexpayments.hpp.sdk:rxp-hpp-android:1.2"
```

### Maven users
Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.realexpayments.hpp.sdk</groupId>
  <artifactId>rxp-hpp-android</artifactId>
  <version>1.2</version>
</dependency>
```

### Manual

You can integrate the Realex Payments Android Library into your project manually.

- Download the the latest release from GitHub:

https://github.com/realexpayments/rxp-android/releases

- Add module 'rxp-hpp-android' into your project to use the HPP Library.
- If you want to use the card data validation library, add the module 'rxp-remote-android' into your project.

## Using the HPP Library

### Instantiate

To instantiate an instance of the HPP manager do the following:

```
HPPManager hppManager = new HPPManager();
```

### Integrate With Your Server

The HPP Manager requires three server URLs.

1) **Request Producer URL**: utilizing one of the Realex HPP server SDKs; this URL creates the necessary request JSON for the component using the shared secret stored on the server side.

2) **HPP URL**: the location where the component sends the encoded request. The default for live transactions is https://pay.realexpayments.com/pay

3) **Response Consumer URL**: utilizing one of the Realex HPP server SDKs; takes the encoded response received back from HPP checks the validity of the hash and decodes the response.

```
hppManager.setHppRequestProducerURL("https://myserver.com/hppRequestProducer");
hppManager.setHppURL("https://pay.realexpayments.com/pay";
hppManager.setHppResponseConsumerURL("https://myserver.com/hppResponseConsumer");
```

### Implement HPPManagerListener In Your Activity

There are three possible outcomes from the HPP interaction

1) It concluded successfully

2) It failed with an error

3) It was cancelled by the user

You can implement the following methods specified in HPPManagerListener to receive back the result from the HPP Manager:

```
@Override public void hppManagerCompletedWithResult(ConsumerResponse consumerResponse) { //success }
@Override public void hppManagerFailedWithError(HPPError error) { //something went wrong }
@Override public void hppManagerCancelled() { //operation was canceled }
```		

### Present Payment Form

Insert the HppManager fragment into your activity as follows:

```
Fragment hppManagerFragment = hppManager.newInstance();
	getFragmentManager()    
        .beginTransaction()      
        .add(R.id.container,hppManagerFragment)      
        .commit()
```

Executing this code, HPP Manager will process the given parameters, get the request from the server, send the encoded request to HPP and present the form received back.

### Consume HPP Response JSON

On the server-side using one of our server SDKs, setup your Response Consumer to take in the response JSON and create the HPP Response:

```
RealexHpp realexHpp = new RealexHpp("secret");
HppResponse hppResponse = realexHpp.responseFromJson(responseJson);
```

## FAQ

### Set HPP Properties

You can also set whatever HPP properties you need to in the component, for example;

```
hppManager.setMerchantId("realexsandbox");
hppManager.setAccount("internet");
hppManager.setAmount("100");
hppManager.setCurrency("EUR");
```

These will be sent to the *Request Producer URL*, your server-side code must be setup to take in these values and pass them to the HPP server-side SDK for them to be included in the request. 	

Note, in addition to the predefined properties, you can add any amount of additional arbitrary properties as follows:

```
hppManager.supplementaryData["UNKNOWN_1"] = "Unknown value 1"
hppManager.supplementaryData["UNKNOWN_2"] = "Unknown value 2"
```		

### Set HPP Properties (Alternative Method)

As an option you could set properties by Bundle:

```
Bundle args = new Bundle();
args.putString(HPPManager.HPPREQUEST_PRODUCER_URL, "https://myserver.com/hppRequestProducer");
args.putString(HPPManager.HPPRESPONSE_CONSUMER_URL, "https://myserver.com/hppResponseProducer");
args.putString(HPPManager.HPPURL, "https://pay.sandbox.realexpayments.com/pay");
args.putString(HPPManager.MERCHANT_ID, "realexsandbox");
args.putString(HPPManager.AMOUNT, "100");
args.putString(HPPManager.CURRENCY, "EUR");
args.putString(HPPManager.ACCOUNT, "internet");
hppManager = hppManager.createFromBundle(args);
```

### Testing		

Realex Payments maintain separate endpoints for live and test transactions. You’ll need to override the HPP URL in the SDK to facilitate testing. Use the code below:

```
hppManager.setHppURL("https://pay.sandbox.realexpayments.com/pay";
```		

## Remote API Validation Library Installation

### Gradle users
Add this dependency to your project's build file:
```
compile "com.realexpayments.remote.sdk:rxp-remote-android:1.0"
```

### Maven users
Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.realexpayments.remote.sdk</groupId>
  <artifactId>rxp-remote-android</artifactId>
  <version>1.0</version>
</dependency>
```

## License

See the LICENSE file.
