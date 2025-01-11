@import ch.heigvd.dai.model.entity.Item
@import ch.heigvd.dai.model.entity.RarityType
@import java.util.LinkedList

@param LinkedList<Item> items

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <title>Items - Add to Inventory</title>
</head>
<body class="bg-light">
<div class="container my-5">
    <h1 class="mb-4">Items Page</h1>
    <p>Here you can see all the items available in the database and add them to your inventory.</p>
    <div class="row">
        @for (Item item : items)
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <h5 class="card-title">
                            <i class="bi bi-sword"></i>
                            ${item.getName()}
                        </h5>
                        <p class="card-text">${item.getDescription()}</p>
                        <p class="mb-1"><strong>Level Required:</strong> ${item.getLevelRequired()}</p>
                        <p>
                            <strong>Rarity:</strong>
                            <span class="badge
                                @if (RarityType.COMMON.equals(item.getRarity().getType())) bg-secondary
                                @elseif (RarityType.UNCOMMON.equals(item.getRarity().getType())) bg-success
                                @elseif (RarityType.RARE.equals(item.getRarity().getType())) bg-primary
                                @elseif (RarityType.EPIC.equals(item.getRarity().getType())) bg-warning text-dark
                                @elseif (RarityType.LEGENDARY.equals(item.getRarity().getType())) bg-danger
                                @endif">
                                ${item.getRarity().toString()}
                            </span>
                        </p>
                        <a href="#" class="btn btn-outline-primary">+</a>
                    </div>
                </div>
            </div>
        @endfor
    </div>
</div>
<a href="/home" class="btn btn-link">Back to home</a>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>