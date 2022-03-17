# Project Structure
The updated project structure is as  follows
1. alumni
2. core - Contains the core features that can be used across the app ex: Tokens, Main Menus etc
   a. common - static values to be used across the app
   b. data - data class
   c. exception - common class to log exception
   d. extension - added function to existing methods
   e. functional - "Either" - this is a utility class to get data and log exceptions or errors
   f. interactor - "UseCase" - This is a new concept added on top of MVVM structure
   g. ui - Common ui elements
   f. utils - Utility class
3. di
   a. RetrofitModule - create retrofit client
   b. RoomModule - create database
   c. {TODO}
4. faculty
5. management
6. parent
7. principal
8. remote
9. room
10. student

# MVVM - Design Pattern (Model-View-ViewModel) more info see link https://whatis.techtarget.com/definition/Model-View-ViewModel#:~:text=Model%2DView%2DViewModel%20(MVVM)%20is%20a%20software%20design,Ken%20Cooper%20and%20John%20Gossman.

Refer to this below link to see the data flow for the above mentioned design pattern

https://www.google.com/url?sa=i&url=https%3A%2F%2Fmedium.com%2Ffirebase-developers%2Fandroid-mvvm-firestore-37c3a8d65404&psig=AOvVaw2zlCJZNlb9bIjCnnBNskN8&ust=1646321448995000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPDyj7vfp_YCFQAAAAAdAAAAABAJ

