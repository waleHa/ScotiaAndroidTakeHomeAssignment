# Scotia Bank Android Take Home Assignment

This Android application uses modern development practices and libraries to communicate with the public GitHub API, providing detailed information about a specific GitHub user.

## Tools and Practices Used

*   Moshi for JSON serialization/deserialization
*   Dagger Hilt for dependency injection
*   Repository Pattern for data management
*   MVVM (Model-View-ViewModel) for architectural pattern
*   Material Design for UI components
*   Singleton Pattern where necessary
*   Coroutines for asynchronous tasks
*   StateFlow for state management
*   View Binding and Data Binding for efficient UI development
*   Use Case layer for decoupling and single responsibility
*   ListAdapter for efficient list management
*   Fragments for modular UI design
*   Unit Testing for code reliability and maintainability

## Functional Requirements

1.  The app accepts a GitHub user's id as input and displays the specified user's avatar and name.
2.  For each public repository owned by the user, the name and description are shown in a scrollable list.
3.  When a repository is selected, the user navigates to a detail screen which displays the details of the specific repo.

## Additional Functional Requirements (for Senior Android Developer Role)

The app displays the total number of forks across all the user's repos on the detail screen. If the total number of forks exceeds 5000, a star badge is shown (annotated by a simple red/gold color on the text).

## Design Requirements

The design and behavior of the app should closely match those shown in the associated screenshots and video. The UI design and animations should replicate the examples as closely as possible.

## API calls

The application uses public GitHub API to fetch user and repository information:

*   **User Information:** `https://api.github.com/users/{userId}`. The necessary fields are `name` (String) and `avatar_url` (String).
*   **User Repositories:** `https://api.github.com/users/{userId}/repos`. The necessary fields are `name` (String), `description` (String), `updated_at` (String), `stargazers_count` (Integer), and `forks` (Integer).

## Architecture

The project uses the Model-View-ViewModel (MVVM) architectural pattern, facilitating clear organization of layers and interaction among them.

## Testing

The project includes automated testing, utilizing unit tests to ensure code reliability and maintainability.

## Evaluation Criteria

The project will be evaluated based on the following:

*   Completion of tasks
*   Architectural design
*   Adherence to functional/design requirements
*   Testability
*   Coding standards and best practices
*   Documentation quality
