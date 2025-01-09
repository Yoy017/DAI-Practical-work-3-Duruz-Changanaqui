@import ch.heigvd.dai.JavalinTest
@import ch.heigvd.dai.model.entity.Player
@import java.util.LinkedList

@param int port
@param LinkedList<Player> players

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BDR x DAI</title>
</head>
<body>
<h1>MMO GAME Project</h1>
<p>Welcome to this first template of the MMO GAME Project application.</p>
<p>Here is the current ranking for all Players registered.</p>
<p>Variables test: ${System.currentTimeMillis()} My Interger param ${port}</p>
<table>
    <tr>
        <th>Name</th>
        <th>Experience</th>
        <th>Balance</th>
        <th>Champion</th>
    </tr>
    @for(Player player : players)
        <tr>
            <td>${player.getName()}</td>
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