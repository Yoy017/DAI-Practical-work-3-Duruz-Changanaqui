@import ch.heigvd.dai.model.entity.Item
@import ch.heigvd.dai.model.entity.RarityType
@import java.util.LinkedList

@param LinkedList<Item> items

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <title>Items - Add to Inventory</title>
    <style>
        .rarity-common { color: #6c757d; }
        .rarity-uncommon { color: #198754; }
        .rarity-rare { color: #0d6efd; }
        .rarity-epic { color: #ffc107; }
        .rarity-legendary { color: #dc3545; }
    </style>
</head>
<body class="bg-light">
<div class="container my-5">
    <h1 class="mb-4">Items Page</h1>
    <p>Here you can see all the items you do not possess yet. Select + to add them to your inventory!</p>
    <div class="row">
        @for (Item item : items)
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <h5 class="card-title">
                            <i class="bi bi-sword"></i>
                            <p class="
                                @if (RarityType.COMMON.equals(item.getRarity().getType())) rarity-common
                                @elseif (RarityType.UNCOMMON.equals(item.getRarity().getType())) rarity-uncommon
                                @elseif (RarityType.RARE.equals(item.getRarity().getType())) rarity-rare
                                @elseif (RarityType.EPIC.equals(item.getRarity().getType())) rarity-epic
                                @elseif (RarityType.LEGENDARY.equals(item.getRarity().getType())) rarity-legendary
                                @endif">
                                ${item.getName()} - ${item.getLevelRequired()}
                            </p>
                        </h5>
                        <p class="card-text">${item.getDescription()}</p>
                        <form method="post" action="/items">
                            <input type="hidden" name="itemId" value="${item.getId()}">
                            <button type="submit" class="btn btn-outline-primary">+</button>
                        </form>
                    </div>
                </div>
            </div>
        @endfor
    </div>
    <a href="/home" class="btn btn-link">Back to home</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
