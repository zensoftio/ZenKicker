# Kicker

## API

### Game Controller

#### Get All

```shell
curl "/api/games"
```

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
curl "/api/games/belong"
```

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
curl "/api/games"
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
curl "/api/players"
```

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
curl "/api/players"
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
curl "/api/players/password-reset"
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
{
  "totalCount": 0,
  "list": [
    {
      "awardType": "string",
      "awardDegree": "string",
      "description": "string"
    }
  ]
}
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