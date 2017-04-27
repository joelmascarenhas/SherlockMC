# SherlockMC
Android Application - Mobile Computing CSE 535
Application to detect users based on accelerometer data 

Android Code Details:
1. Background Service Referred from this URL - https://github.com/nicolashahn/BackgroundAccelerometer
2. REST Client Calls - Retrofit API - http://square.github.io/retrofit/
3. HTTP Interceptor (For logging HTTP Communication)- https://github.com/square/okhttp/wiki/Interceptors
4. Google GSON(For testing JSON Models & Communication) - https://github.com/google/gson
5. Used Vector Assets from Google for Icons in the application
6. Used Material Design Guides for designing UI pages

Code Explanation:

/beans - Contains all the Bean classes that are used in the project for communication between layers
AtomData - Bean class to hold unit accelerometer data 
AuthReply - Authentication reply model from cloud - Used by Retrofit to catch response from server
GPSData - Model to hold GPS information as Latitude, Longitude & CityName
RegisterReply - Model to hold registration status from cloud 
TrainData - model to hold Unit 5 minute data that will be sent to the server from phone, contains 900 AtomData object and GPSData object with unique identifier for the user i.e. e-mail
TrainingReply - model to hold reply from cloud after sending data
UpdateReply - model to hold update user profile response from server
User - model to hold user's details 


/restlayer - Contains all the REST related stuffs, whatever be the rest call will be propagated from this layer
APIEndpoints - Interface fed into the Retrofit API holding list of endpoints and their responses defined
AuthUserLogin - Rest layer class that propagates REST call for Login functinality
RegisterUser - Rest layer class that packs data and REST call for register functinality
RestClient - Class that fetches the Retrofit object built with HTTP Interceptor
UpdateUser - Rest layer class that packs data and REST call for update user profile functinality
UploadTrainingData - Rest layer class that packs data and REST call for uploading data from phone to server functinality

/service - contains all the helper classes that will be used by the application code

/utils - contains all utility methods of the project

Activities:
1. LoginActivity
2. Register
3. Dashboard
4. UpdateProfile

Services:
1. DataCollectorAccel



