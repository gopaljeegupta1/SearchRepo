# SearchRepo App

## Problem statement ##

Build a mobile application using GitHub API to search through repositories. Select a repository and see the details of the project and contributors. On selecting contributor, list the repositories tagged to the contributors. The application consists of two screens. The home screen where you search and display the list of repositories. Repo Details screen where you list the necessary information about the repository. Necessary information is listed below.
 
  * Home: <br/>
      o   A search bar to search from git APIs
      o   A recycler view using card view to display the search results.
      o   The results count should be limited to 10 per page.
      o   Clicking on an item to go to Repo Details Activity.

  * Repo Details: <br/>
      o   This Activity should have a detailed description of the selected item.
      o   Details such as Image, Name, Project Link, Description, Contributors should be displayed.
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
  * Architecture
      o   MVVM Architecture (View - DataBinding - ViewModel - Model)
      o   Repository pattern
  * Glide - loading images.
  * Retrofit2 & OkHttp3 - construct the REST APIs and paging network data.
  * Material-Components - Material design components like ripple animation, cardView.
   
## Screenshots   

<div style="display: flex;width: 100%;justify-content: space-between;">
    <img style="margin-right: 10px;" src="/screenshots/1.jpeg">
    <img style="margin-right: 10px;" src="/screenshots/2.jpeg">
    <img style="margin-right: 10px;" src="/screenshots/3.jpeg">
    <img style="margin-right: 10px;" src="/screenshots/4.jpeg">
</div>

--- Don't forget to comments for more improvement ---
