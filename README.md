# OpenInAppTask
OpenInAppTask is an Android application developed in Kotlin. It follows the MVVM (Model-View-ViewModel) architecture and incorporates several key components such as coroutines, remote repository, LiveData, ViewModel, RecyclerView, and data binding.

The primary feature of the application is a main dashboard screen, which is displayed within an activity. This screen serves as the central hub for presenting various information to the user. The data for the dashboard is obtained from the "api/v1/dashboardNew" API.

The dashboard screen consists of the following components:

- **Username**: This section displays the username of the user associated with the application.

- **User Top Clicks**: Here, the application showcases the top clicks made by the user. This information could be presented as a list or any other suitable representation to highlight the most frequently accessed items.

- **Recent Clicks**: This section presents the user's most recent clicks. It may be displayed as a list or another suitable format to show the recently accessed items.

- **Overview Line Chart**: The dashboard includes an overview line chart that visualizes the relationship between the date and the number of clicks. The x-axis represents the months, while the y-axis represents the click count. This line chart helps users understand the trends and patterns in their click activity over time.

The application leverages the **MVVM architecture** to separate concerns, enabling efficient data handling, UI representation, and business logic management. **Coroutines** are utilized for asynchronous programming, ensuring smooth execution of tasks without blocking the main thread.

**LiveData** is employed to observe and update UI components whenever there are changes in the data. ViewModel serves as a lifecycle-aware container for holding and managing UI-related data, maintaining data consistency across configuration changes.

**RecyclerView** is used to efficiently display lists or collections of items, such as the user's top clicks and recent clicks. It provides optimized view recycling, improving performance when dealing with large datasets.

**Data binding** facilitates the connection between the UI elements and the underlying data sources. This enables seamless updates and interactions between the user interface and the application logic, enhancing the overall user experience.
