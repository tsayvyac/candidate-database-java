# Task

![ER diagram](/diagram/erd.png)\
ER diagram

- Optional object was used to avoid NullPointerException. 
- I also actively used the Stream API to improve code readability and keep the code clean. 
- An additional table with composite primary key was created to implement the many-to-many relationship. 
- To define composite primary key in JPA I used @EmbeddedId and @Embeddable annotations.

## RestAPI Endpoints
Candidate:
- **/api/candidate** -- Add candidate, retrieve candidate data
- **/api/candidate/{id}** -- Delete/update candidate data, retrieve candidate details

Technology:
- **/api/tech** -- Add technology, retrieve technology
- **/api/tech/{id}** -- Delete/update technology, retrieve technology details
- **/api/tech/stats** -- Retrieve a list with the name of technology and the count of candidates using it

## Sample requests
/api/candidate:
```json
{
    "firstName": "Adam",
    "lastName": "Vojtech",
    "age": 22,
    "technologies": [
        {
            "name": "Typescript",
            "level": 4,
            "note": "Junior Typescript Developer"
        },
        {
            "name": "Java",
            "level": 10,
            "note": "Senior"
        }
    ]
}
```
/api/tech:
```json
{
    "name": "Typescript"
}
```
/api/candidate/{id}/new
```json
[
    {
        "name": "Kotlin",
        "level": 2,
        "note": "Add new programming language"
    }
]
```
## Sample responses
/api/candidate
```json
[
    {
        "id": 1,
        "firstName": "Adam",
        "lastName": "Vojtech",
        "useTechnologies": [
            {
                "name": "Typescript",
                "level": 1
            },
            {
                "name": "Java",
                "level": 10
            }
        ]
    }
]
```
/api/candidate/{id}
```json
{
  "id": 1,
  "firstName": "Adam",
  "lastName": "Vojtech",
  "age": 22,
  "useTechnologies": [
    {
      "id": 1,
      "name": "Typescript",
      "level": 4,
      "note": "Junior Typescript Developer"
    },
    {
      "id": 2,
      "name": "Java",
      "level": 10,
      "note": "Senior"
    }
  ]
}
```
/api/tech/stats
```json
[
    {
        "name": "Java",
        "count": 31
    },
    {
        "name": "Typescript",
        "count": 11
    }
]
```