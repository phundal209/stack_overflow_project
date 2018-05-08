--------------------------------------------------------------------------------------------------------

Stack Overflow Project

Overview:

You will need to connect to Stackoverflow Users API Endpoint and retrieve the first page of data. FULL API documentation
Display the retrieved data through a RecyclerView or a ListView.
We expect from you to display at least username, badges and gravatar for every user.
While the gravatar is being downloaded, the UI should show a loading animation.
Each of the photos should be downloaded only once and stored for offline usage.
The UI should always be responsive.

--------------------------------------------------------------------------------------------------------

Technical Notes:

Additional Libraries Used

1. Butterknife : View injection. Used this for easily injecting views and reducing boiler plate code.
2. Support Design Library : Used for RecyclerView 
3. Retrofit/OkHttp : Networking libraries used to make rest layer easy to maintain. Also used an interceptor to make it easy to view logs of output.
4. RxJava/RxAndroid : Used for making network requests and mapping them to the UI easily through observables.
5. Realm : Internal database used as a caching mechanism. 
6. GSON : Deserialization library 
7. Picasso : Image loading/rendering library. More lightwieght than Glide, so used it to keep apk size reasonable here.
8. Picasso transformation library : Purely for UI usage. Avatars are generally circular so I made them have
a circular transformation.


--------------------------------------------------------------------------------------------------------

Architecture

Tried to keep it very simple and bried. Used a MVP pattern here, where I allowed the view to handle the view rendering logic, and the presenter to handle the business logic of binding the view with the model.

The Models were Realm Objects and the only Views I had were the MainActivity and the Adapter.
I allowed the Adapter's view holder to have it's own binding in the onBindViewHolder. I did not do reverse
delegation out of the adapter class because it did not make sense for this use case. 

The Api layer did the network requesting and saving to the cache, and allowed ApiService to act as a repository unit
that the presenter pulled from. 
