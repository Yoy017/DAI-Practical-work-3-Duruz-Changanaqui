@import ch.heigvd.dai.model.entity.Player
@import java.util.LinkedList

@param LinkedList<Player> players

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>BDR x DAI</title>
</head>
<body>
<h1>MMO GAME Project</h1>
<p>Welcome to this first template of the MMO GAME Project application.</p>
<p>Here is the current ranking for all Players registered.</p>
<table>
    <tr>
        <th>Name</th>
        <th>Experience</th>
        <th>Balance</th>
        <th>Champion</th>
    </tr>
    @for(Player player : players)
        <tr>
            <td><a href="/armory?player=${player.getName()}">${player.getName()}</a></td>
            <td>${player.getExperience()}</td>
            <td>${player.getBalance()}</td>
            <td>${player.getChampion()}</td>
        </tr>
    @endfor
</table>

<p>Click here to start the game</p>
<button>Play</button>
</body>
</html>