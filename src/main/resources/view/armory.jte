@import ch.heigvd.dai.model.entity.Inventory
@import ch.heigvd.dai.model.entity.Item
@import ch.heigvd.dai.model.entity.Slot
@import java.util.LinkedList

@param Inventory inventory

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BDR x DAI</title>
</head>
<body>
    <h1>Armory page</h1>
    <p>Here you can see all the weapons and armors that you have in your inventory</p>
    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Level required</th>
            <th>Rarity</th>
        </tr>

        @for (Slot slot : inventory.getSlots())
            <tr>
                <td>${slot.getItem().getName()}</td>
                <td>${slot.getItem().getDescription()}</td>
                <td>${slot.getItem().getLevelRequired()}</td>
                <td>${slot.getItem().getRarity().toString()}</td>
            </tr>
        @endfor
    </table>
    <a href="/home">Back to home</a>
</body>
</html>