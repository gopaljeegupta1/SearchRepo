# SearchRepo App

## Problem statement ##

Build a mobile application using GitHub API to search through repositories. Select a repository and see the details of the project and contributors. On selecting contributor, list the repositories tagged to the contributors. The application consists of two screens. The home screen where you search and display the list of repositories. Repo Details screen where you list the necessary information about the repository. Necessary information is listed below.
 
  * Home: <br/>
      o   A search bar to search from git APIs <br/>
      o   A recycler view using card view to display the search results. <br/>
      o   The results count should be limited to 10 per page. <br/>
      o   Clicking on an item to go to Repo Details Activity. <br/>

  * Repo Details: <br/>
      o   This Activity should have a detailed description of the selected item. <br/>
      o   Details such as Image, Name, Project Link, Description, Contributors should be displayed. <br/>
      o   When you click on the "project link" section, you have to open a web view to show the content of the link available as shown in the video attached.
      
## Skills Set

  * Kotlin 
  * Coroutines for asynchronous.
  * Dagger-Hilt for dependency injection.
  * JetPack
  * LiveData - notify domain layer data to views.
  * Lifecycle - dispose of observing data when lifecycle state changes.
  * ViewModel - UI related data holder, lifecycle aware.
  * Navigation Component - handle everything needed for in-app navigation.
  * Data Binding - declarative bind observable data to UI elements.
  * Architecture <br/>
      o   MVVM Architecture (View - DataBinding - ViewModel - Model) <br/>
      o   Repository pattern
  * Glide - loading images.
  * Retrofit2 & OkHttp3 - construct the REST APIs and paging network data.
  * Material-Components - Material design components like ripple animation, cardView.
   
## Screenshots   

<div style="display: flex;width: 100%;justify-content: space-between;">
    <img style="margin-right: 10px;" src="/screenshots/1.jpg" width="250" height="600">   
    <img style="margin-right: 10px;" src="/screenshots/2.jpg" width="250" height="600"> 
    <img style="margin-right: 10px;" src="/screenshots/3.jpg" width="250" height="600"> 
</div>

<br/><br/>
--- Don't forget to comments for more improvement ---
