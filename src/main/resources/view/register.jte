@import java.util.List

@param List<String> classes
@param String errorMessage

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Character</title>
</head>
<body>
<h1>Create a New Character</h1>
<p>Fill out the form below to create a new character for the game.</p>

<form action="/register" method="post">
    <label for="character-name">Character Name:</label>
    <input type="text" id="character-name" name="name" required>
    @if(errorMessage != null)
        <span style="color: red;">${errorMessage}</span>
    @endif
    <br><br>

    <label for="character-class">Choose a Profession:</label>
    <select id="character-class" name="profession" required>
        @for(String className : classes)
        <option value="${className}">${className}</option>
        @endfor
        @if(classes.isEmpty())
        <option value="warrior">Warrior</option>
        <option value="mage">Mage</option>
        @endif
    </select>
    <br><br>

    <button type="submit">Create Character</button>
</form>

</body>
</html>