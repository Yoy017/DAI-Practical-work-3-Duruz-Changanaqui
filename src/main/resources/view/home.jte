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
    <title>MMO GAME Project</title>
</head>
<body>
<div class="container mt-5">
    <div class="text-center">
        <h1 class="display-4">MMO GAME Project</h1>
        <p class="lead">Welcome to this first template of the MMO GAME Project application.</p>
        <p>Here is the current ranking for all Players registered.</p>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Experience</th>
                <th scope="col">Balance</th>
                <th scope="col">Champion</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            @for(Player player : players)
                <tr>
                    <td><a href="/armory?player=${player.getName()}" class="text-decoration-none">${player.getName()}</a></td>
                    <td>${player.getExperience()}</td>
                    <td>${player.getBalance()}</td>
                    <td>${player.getChampion()}</td>
                    <td>
                        <!-- Bouton pour supprimer un joueur -->
                        <form action="/delete-player" method="post" style="display:inline;">
                            <input type="hidden" name="playerName" value="${player.getName()}">
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
    <p>Create a New Character</p>
    <form action="/register" method="get">
        <button type="submit">Register</button>
    </form>
</div>

<!-- Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-whK5zvMVD5FnOFtmZKtbRjBT07C0UjGvd3ycmTSHxnuGk5EmRXf3RZpLNM9z3KeJ" crossorigin="anonymous"></script>
</body>
</html>