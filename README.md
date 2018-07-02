# Kicker

## API

### Marks

* `pageable`

> Target - URL
> 
> Indicate - URL marked by `pageble` supports request parameters described below
> 
> Request params:
>   1. offset - describes the offset for the set of results (only positive integers, default value - `0`)
>   2. limit - describes the limit of entities in the result set (only positive integers, default value - `10`)

* `sorting`

> Target - URL
> 
> Indicate - allow sorting by acceptable fields
> 
> Request params:
>   1. sortBy - describes the sorting field of entity (default value - `id`)
>   2. sortDirection - describes the sorting direction(`ASC` or `DESC`, default value - `ASC`) 

* `optional`

> Target - dto's fields
> 
> Indicate - nullable fields

### Game Controller

#### Get All

```shell
curl "/api/games" - `pageable` `sorting`
```
           
 sortBy:
 * id
 * date
 
> Response:

```json
{
  "totalCount": 0,
  "list": [
    {
      "id": 0,
      "redPlayer1Id": 0,
      "redPlayer2Id": 0,
      "yellowPlayer1Id": 0,
      "yellowPlayer2Id": 0,
      "redGoals": 0,
      "yellowGoals": 0,
      "date": "string",
      "reportedById": 0
    }
  ],
  "players": [
    {
      "id": 0,
      "username": "string",
      "firstName": "string",
      "lastName": "string",
      "active": true,
      "currentRating": 0
    }
  ]
}
```

#### Get

```shell
curl "/api/games/{gameId}"
```

> Response:

```json
{
  "id": 0,
  "redPlayer1Id": 0,
  "redPlayer2Id": 0,
  "yellowPlayer1Id": 0,
  "yellowPlayer2Id": 0,
  "redGoals": 0,
  "yellowGoals": 0,
  "date": "string",
  "reportedById": 0
}
```

#### Get All Belong Games

```shell
curl "/api/games/belong/{playerId}" - `pageable` `sorting`
```

 sortBy:
 * id
 * date
 
> Response:

```json
{
  "totalCount": 0,
  "list": [
    {
      "id": 0,
      "redPlayer1Id": 0,
      "redPlayer2Id": 0,
      "yellowPlayer1Id": 0,
      "yellowPlayer2Id": 0,
      "redGoals": 0,
      "yellowGoals": 0,
      "date": "string",
      "reportedById": 0
    }
  ],
  "players": [
    {
      "id": 0,
      "username": "string",
      "firstName": "string",
      "lastName": "string",
      "active": true,
      "currentRating": 0
    }
  ]
}
```

#### Game Registration

```shell
curl "/api/games/{playerId}"
    -X POST
```

> Request:

```json
{
  "redPlayer1": "string",
  "redPlayer2": "string",
  "yellowPlayer1": "string",
  "yellowPlayer2": "string",
  "redGoals": 0,
  "yellowGoals": 0
}
```

> Response:

```json
{
  "id": 0,
  "redPlayer1Id": 0,
  "redPlayer2Id": 0,
  "yellowPlayer1Id": 0,
  "yellowPlayer2Id": 0,
  "redGoals": 0,
  "yellowGoals": 0,
  "date": "string",
  "reportedById": 0
}
```

### Player Controller

#### Get All

```shell
curl "/api/players" - `pageable` `sorting`
```

sortBy:
* id
* rating

> Response:

```json
{
  "totalCount": 0,
  "list": [
    {
      "id": 0,
      "username": "string",
      "firstName": "string",
      "lastName": "string",
      "active": true,
      "currentRating": 0
    }
  ]
}
```

#### Create Player

```shell
curl "/api/players"
    -X POST
```

> Request:

```json
{
  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "password": "string",
  "retypePassword": "string"
}
```

> Response:

```json
{
  "id": 0,
  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "active": true,
  "currentRating": 0
}
```

#### Update Data Player

```shell
curl "/api/players/{playerId}"
    -X PUT
```

> Request:

```json
{
  "username": "string",
  "firstName": "string",
  "lastName": "string"
}
```

> Response:

```json
{
  "id": 0,
  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "active": true,
  "currentRating": 0
}
```

#### Update Password Player

```shell
curl "/api/players/{playerId}/password-reset"
    -X PUT
```

> Request:

```json
{
  "currentPassword": "string",
  "newPassword": "string",
  "retypePassword": "string"
}
```

> Response:

```json
{
  "id": 0,
  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "active": true,
  "currentRating": 0
}
```

#### Get

```shell
curl "/api/players/{playerId}"
```

> Response:

```json
{
  "id": 0,
  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "active": true,
  "currentRating": 0
}
```

#### Get Current Player

```shell
curl "/api/players/current"
```

> Response:

```json
{
  "id": 0,
  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "active": true,
  "currentRating": 0
}
```

### Award Controller

#### Get All By Player

```shell
curl "/api/awards/{playerId}"
```

> Response:

```json
[
    {
      "awardType": "string",
      "awardDegree": "string",
      "description": "string"
    }
]
```

### Dashboard Rating Controller

#### Get All By Player

```shell
curl "/api/dashboard-rating/{playerId}"
```

> Response:

```json
{
  "ratings": [
    {
      "delta": 0,
      "weeksAgo": 0
    }
  ],
  "initialRating": 0
}
```

### Errors

> Exception response:

```json
{
    "status": 0,
    "message": "string",
    "errors": [
        {
            "code": "string",
            "field": "string",
            "message": "string"
        },
        {
            "code": "string"
        }
    ]
}
```

The Kicker API uses the following error format:

#### Exception response:

The attributes:

Attribute | Type | Description
--------- | ---- | -----------
status | Integer | Http code
message | String | The description of exception
errors | [ErrorDto](#error-dto)[] |  Array error objects

#### Error dto:

The attributes:

Attribute | Type | Description
--------- | ---- | -----------
code | String | Description error
field | String | The name of field - *optional*
message | String | The description of error - *optional*

#### Error Statuses:

Error Status | Meaning
---------- | -------
400 | Bad Request - Your request is invalid.
401 | Unauthorized - Your API key is wrong.
403 | Forbidden - You don`t have an appropriate roles.
404 | Not Found - The specified Kicker could not be found.
405 | Method Not Allowed - You tried to access a Kicker with an invalid method.
406 | Not Acceptable - You requested a format that isn't json.
500 | Internal Server Error - We had a problem with our server. Try again later.