# MMO Project API

The MMO Project API allows managing characters, their inventory, and related data. It uses the HTTP protocol and the JSON format.

The API is based on the CRUD pattern. It has the following operations:

- Create a new character
- Get many characters that you can filter by name and/or profession
- Get one character by its ID
- Update a character
- Delete a character
- Manage character inventory and items

Characters can also access their profile to validate their information using a session.

## Endpoints

### Get characters

- `GET /home`

Get many characters.

```bash
  curl -X GET http://localhost:8080/home
```
#### Response
The response contains an HTML page displaying the list of players.

#### Status Codes
- `200` (OK) - The list of players was successfully retrieved.
- `500` (Internal Server Error) - An error occurred while retrieving the players.

### Delete a player using POST

- **Endpoint**: `POST /delete-player`  
  Delete a player by sending their name in the form data.

#### Example `curl` command:

```bash
curl -X POST http://localhost:8080/delete-player \
     -d "playerName=PlayerName"
```
#### Parameters
playerName (required) - The name of the player to delete.
Response
A plain text message indicating the result of the operation.

#### Status Codes
- `200` (OK) - The player was successfully deleted.
- `400` (Bad Request) - The playerName parameter is missing or empty.
- `404` (Not Found) - The specified player does not exist.
- `500` (Internal Server Error) - An error occurred while deleting the player.

### Delete a player using DELETE

- **Endpoint**: `DELETE /delete-player/{name}`  
  Delete a player by specifying their name in the request path.

#### Example `curl` command:

```bash
curl -X DELETE http://localhost:8080/delete-player/PlayerName
```

#### Parameters
{name} (required) - The name of the player to delete, included in the URL.
Response
A plain text message indicating the result of the operation.

#### Status Codes
- `200` (OK) - The player was successfully deleted.
- `400` (Bad Request) - The name parameter is missing or empty.
- `404` (Not Found) - The specified player does not exist.
- `500` (Internal Server Error) - An error occurred while deleting the player.

### Get available classes

- `GET /register`

#### Example `curl` command:
```bash
curl -X GET http://localhost:8080/register
```

#### Request

Empty

#### Response

Display all classes available

#### Status codes

- `200` (No Content) - OK

### Register new Character

- `POST /register`

#### Example `curl` command:
```bash
curl -X GET http://localhost:8080/register \
    -d '{"name":"NomDuJoueur","profession":"ProfessionDuJoueur"}'
```

#### Request

- `name` - Name of the player
- `profession` - Class of the player

#### Status codes

- `200` (OK) - The character has been successfully created
- `400` (Bad Request) - Character name already exist

### Register new Character

- `POST /register/{name}/{profession}`

#### Example `curl` command:
```bash
curl -v -X POST http://localhost:8080/register/MyName/Guerrier
```

#### Request

- `name` - Name of the player
- `profession` - Class of the player

#### Status codes

- `200` (OK) - The character has been successfully created
- `400` (Bad Request) - Character name already exist

### Accept Quest

- `PATCH /quest/accept/{playerName}/{questName}`

#### Example `curl` command:
```bash
curl -v -X PATCH http://localhost:8080/quest/accept/EvilSoul/Friend%20of%20zanahorias
```

#### Request

The request path must contain the name of the character.
The request path must contain the name of the quest.

#### Response

The response body is empty.

#### Status codes

- `200` (OK) - The quest has been accepted
- `404` (Not Found) - The character or quest does not exist

### Complete Quest

- `PATCH /quest/complete/{playerName}/{questName}`

#### Example `curl` command:
```bash
curl -v -X PATCH http://localhost:8080/quest/complete/EvilSoul/Friend%20of%20zanahorias
```

#### Request

The request path must contain the name of the character.
The request path must contain the name of the quest.

#### Response

The response body is empty.

#### Status codes

- `200` (OK) - Quest completed
- `404` (Not Found) - The character or Quest does not exist

### Delete Quest From Journal

- `DELETE /quest/{playerName}/{questName}`

#### Example `curl` command:
```bash
curl -v -X DELETE http://localhost:8080/quest/EvilSoul/Friend%20of%20zanahorias
```
#### Request

The request path must contain the name of the character.
The request path must contain the name of the quest.

#### Response

The response body is empty.

#### Status codes

- `200` (OK) - The quest has been successfully deleted
- `404` (Not Found) - The character or quest does not exist

### Get Player Inventory

- `GET /armory`

#### Example `curl` command:
```bash
curl -X GET "http://localhost:8080/URL?player=EvilSoul"
```

#### Request

The request path must contain the name of the character.

#### Response

HTML of the player's equipements

#### Status codes

- `200` (OK) - Player items retrieve

### Player equipement Management

- `POST /armory`

#### Example `curl` command:
Delete
```bash
curl -X POST http://localhost:8080/armory \
  -d "_method=delete" \
  -d "itemName=ItemToRemove"
```
Equip
```bash
curl -X POST http://localhost:8080/armory \
  -d "_method=equip" \
  -d "itemName=ItemToEquip"
```

Unequip
```bash
curl -X POST http://localhost:8080/armory \
  -d "_method=unequip" \
  -d "itemName=ItemToUnequip"
```

#### Request

Must contain the name of the method to use.
Must contain the name of the item to manipulate.

#### Response

HTML of the player's equipements

#### Status codes

- `200` (OK) - Player items retrieve
- `404` (NOT FOUND) - method or item not found

### Add Item To Player Inventory

- `POST /items`

#### Example `curl` command:
```bash
curl -X POST http://localhost:8080/items \
  -b "player=EvilSoul" \
  -d "itemId=123"
```

#### Request

Must Contain player name and item Id

#### Response

Empty Body

#### Status codes

- `200` (OK) - Player items retrieve
- `404` (NOT FOUND) - player name or item id not found

### Get all Items available

- `GET /items`

#### Example `curl` command:
```bash
curl -X GET http://localhost:8080/items \
  -b "player=EvilSoul"
```

#### Request

Must Contain player name

#### Response

Empty Body

#### Status codes

- `200` (OK) - items retrieve
- `404` (NOT FOUND) - player name not found