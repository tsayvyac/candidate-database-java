# Task

![ER diagram](/diagram/erd.png)\
ER diagram

- Optional object was used to avoid NullPointerException. 
- I also actively used the Stream API to improve code readability and keep the code clean. 
- An additional table with composite primary key was created to implement the many-to-many relationship. 
- To define composite primary key in JPA I used @EmbeddedId and @Embeddable annotations.

### Bonus
I created [endpoint](#bonus-sample) to find out how many candidates are using this technology.

## RestAPI Endpoints
Candidate:
- **/api/candidate/fetchAll** -- fetch all candidates.
- **/api/candidate/add** -- add new candidate.
- **/api/candidate/delete/{id}** -- delete candidate with id: {id}.
- **/api/candidate/update/{id}** -- update candidate with id: {id}.
- **/api/candidate/addTech/{id}** -- add technology to candidate with id: {id}.
- **/api/candidate/fetchDetails/{id}** -- fetch candidate details with id: {id}.

Technology:
- **/api/tech/fetchAll** -- fetch all technologies.
- **/api/tech/add** -- add new technology.
- **/api/tech/delete/{id}** -- delete technology with id: {id}.
- **/api/tech/update/{id}** -- update technology with id: {id}.
- **/api/tech/fetchDetails** -- fetch technology details with id: {id}.
- **/stats** -- fetch a list with the name of technology and the count of candidates using it.

## Sample requests
/api/candidate/add:
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
/api/tech/add:
```json
{
    "name": "Typescript"
}
```
/api/candidate/addTech/{id}
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
/api/candidate/fetchAll
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
/api/candidate/fetchDetails/{id}
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
## Bonus sample
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