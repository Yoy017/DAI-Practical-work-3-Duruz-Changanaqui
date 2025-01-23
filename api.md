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

### Create a new character

- `POST /register`

Create a new character.

#### Request

The request body must contain a JSON object with the following properties:

- `name` - The name of the character
- `profession` - The profession of the character

#### Response

The response body contains a JSON object with the following properties:

- `id` - The unique identifier of the character
- `name` - The name of the character
- `profession` - The profession of the character

#### Status codes

- `201` (Created) - The character has been successfully created
- `400` (Bad Request) - The request body is invalid
- `409` (Conflict) - The character already exists

### Get many characters

- `GET /characters`

Get many characters.

#### Request

The request can contain the following query parameters:

- `name` - The name of the character
- `profession` - The profession of the character

#### Response

The response body contains a JSON array with the following properties:

- `id` - The unique identifier of the character
- `name` - The name of the character
- `profession` - The profession of the character

#### Status codes

- `200` (OK) - The characters have been successfully retrieved

### Get one character

- `GET /characters/{id}`

Get one character by its ID.

#### Request

The request path must contain the ID of the character.

#### Response

The response body contains a JSON object with the following properties:

- `id` - The unique identifier of the character
- `name` - The name of the character
- `profession` - The profession of the character

#### Status codes

- `200` (OK) - The character has been successfully retrieved
- `404` (Not Found) - The character does not exist

### Update a character

- `PUT /characters/{id}`

Update a character by its ID.

#### Request

The request path must contain the ID of the character.

The request body must contain a JSON object with the following properties:

- `name` - The name of the character
- `profession` - The profession of the character

#### Response

The response body contains a JSON object with the following properties:

- `id` - The unique identifier of the character
- `name` - The name of the character
- `profession` - The profession of the character

#### Status codes

- `200` (OK) - The character has been successfully updated
- `400` (Bad Request) - The request body is invalid
- `404` (Not Found) - The character does not exist

### Delete a character

- `DELETE /characters/{id}`

Delete a character by its ID.

#### Request

The request path must contain the ID of the character.

#### Response

The response body is empty.

#### Status codes

- `204` (No Content) - The character has been successfully deleted
- `404` (Not Found) - The character does not exist

### Profile

- `GET /profile`

Get the current character (the character that is logged in).

#### Request

The request body is empty.

#### Response

The response body contains a JSON object with the following properties:

- `id` - The unique identifier of the character
- `name` - The name of the character
- `profession` - The profession of the character

#### Status codes

- `200` (OK) - The character has been successfully retrieved
- `401` (Unauthorized) - The character is not logged in

### Get character inventory

- `GET /characters/{id}/inventory`

Get the inventory of a character by its ID.

#### Request

The request path must contain the ID of the character.

#### Response

The response body contains a JSON object with the following properties:

- `slots` - A list of slots in the inventory, each containing:
    - `type` - The type of the slot (e.g., "Bag", "Equipment")
    - `item` - The item in the slot, with the following properties:
        - `id` - The unique identifier of the item
        - `name` - The name of the item
        - `description` - The description of the item
        - `type` - The type of the item
        - `rarity` - The rarity of the item
        - `requiredLevel` - The required level to use the item

#### Status codes

- `200` (OK) - The inventory has been successfully retrieved
- `404` (Not Found) - The character does not exist

### Add item to inventory

- `POST /characters/{id}/inventory`

Add an item to the inventory of a character by its ID.

#### Request

The request path must contain the ID of the character.

The request body must contain a JSON object with the following properties:

- `itemId` - The ID of the item to add

#### Response

The response body is empty.

#### Status codes

- `204` (No Content) - The item has been successfully added to the inventory
- `400` (Bad Request) - The request body is invalid
- `404` (Not Found) - The character or item does not exist

### Equip item

- `PUT /characters/{id}/inventory/equip`

Equip an item from the inventory of a character by its ID.

#### Request

The request path must contain the ID of the character.

The request body must contain a JSON object with the following properties:

- `itemId` - The ID of the item to equip

#### Response

The response body is empty.

#### Status codes

- `204` (No Content) - The item has been successfully equipped
- `400` (Bad Request) - The request body is invalid
- `404` (Not Found) - The character or item does not exist

### Unequip item

- `PUT /characters/{id}/inventory/unequip`

Unequip an item from the inventory of a character by its ID.

#### Request

The request path must contain the ID of the character.

The request body must contain a JSON object with the following properties:

- `itemId` - The ID of the item to unequip

#### Response

The response body is empty.

#### Status codes

- `204` (No Content) - The item has been successfully unequipped
- `400` (Bad Request) - The request body is invalid
- `404` (Not Found) - The character or item does not exist

### Delete item from inventory

- `DELETE /characters/{id}/inventory`

Delete an item from the inventory of a character by its ID.

#### Request

The request path must contain the ID of the character.

The request body must contain a JSON object with the following properties:

- `itemId` - The ID of the item to delete

#### Response

The response body is empty.

#### Status codes

- `204` (No Content) - The item has been successfully deleted from the inventory
- `400` (Bad Request) - The request body is invalid
- `404` (Not Found) - The character or item does not exist