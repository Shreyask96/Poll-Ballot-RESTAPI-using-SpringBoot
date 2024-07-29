Poll Ballot API

Running the Application

Clone the repository
Navigate to the project directory
Run mvn spring-boot:run
Access the API at http://localhost:8080
API Endpoints

POST /entercandidate?name={name}: Adds a candidate
POST /castvote?name={name}: Casts a vote for a candidate
GET /countvote?name={name}: Gets the vote count for a candidate
GET /listvote: Lists all candidates and their vote counts
GET /getwinner: Gets the candidate with the most votes
