# Access-App-Exercise

This is John Pan Access android MVVM exercise.

I used Picasso to load image and used rx2-android-networking to do the networking.
For the circle Image I created the CircleTransform for Picasso.


ViewModel use dependency injection to get repository. Repository also use dependency injection to get ApiService.
And I implement ApiService in ApiServiceImpl to get the Model. Activity Observe ViewModel's live data to change ui.