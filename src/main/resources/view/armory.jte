@import ch.heigvd.dai.model.entity.Inventory
@import ch.heigvd.dai.model.entity.Item
@import ch.heigvd.dai.model.entity.Slot
@import ch.heigvd.dai.model.entity.SlotType
@import ch.heigvd.dai.model.entity.ItemType

@param Inventory inventory

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <title>Armory - Inventory</title>
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
    <h1 class="mb-4">Armory Page</h1>
    <p>Manage your equipment and inventory below.</p>
    <div class="row">
        <!-- Player Statistics Section -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm border-0">
                <div class="card-body">
                    <h5 class="card-title">Player Statistics</h5>
                    <ul class="list-group">
                        <li class="list-group-item">Level: <strong>25</strong></li>
                        <li class="list-group-item">Health: <strong>120 HP</strong></li>
                        <li class="list-group-item">Mana: <strong>80 MP</strong></li>
                        <li class="list-group-item">Strength: <strong>15</strong></li>
                        <li class="list-group-item">Agility: <strong>10</strong></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="col-md-8 mb-4">
            <!-- Equipped Items Section -->
            <div class="card shadow-sm border-0">
                <div class="card-body">
                    <h5 class="card-title">Equipped Items</h5>
                    <div class="row">
                        @for (Slot slot : inventory.getSlots())
                            ${slot.getType()}
                            @if (slot.getType().equals(SlotType.EQUIPMENT))
                                <div class="col-md-6 mb-3">
                                    <div class="border p-2 rounded">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <strong class="rarity-${slot.getItem().getRarity().toString().toLowerCase()}">
                                                ${slot.getItem().getName()} <small> - ${slot.getItem().getLevelRequired()}</small>
                                            </strong>
                                            <div class="dropdown">
                                                <button class="btn btn-sm btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <i class="bi bi-three-dots"></i>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li>
                                                        <form action="/armory" method="post">
                                                            <input type="hidden" name="_method" value="unequip">
                                                            <input type="hidden" name="itemName" value="${slot.getItem().getName()}">
                                                            <button type="submit" class="dropdown-item">Unequip</button>
                                                        </form>
                                                    </li>
                                                    <li>
                                                        <form action="/armory" method="post">
                                                            <input type="hidden" name="_method" value="delete">
                                                            <input type="hidden" name="itemName" value="${slot.getItem().getName()}">
                                                            <button type="submit" class="dropdown-item text-danger">Delete</button>
                                                        </form>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <p>${slot.getItem().getDescription()}</p>
                                    </div>
                                </div>
                            @endif
                        @endfor
                    </div>
                </div>
            </div>

            <!-- Inventory Section -->
            <div class="card mt-4 shadow-sm border-0">
                <div class="card-body">
                    <h5 class="card-title">Inventory</h5>
                    <div class="row">
                        @for (Slot slot : inventory.getSlots())
                            @if (slot.getType().equals(SlotType.BAG))
                                <div class="col-md-4 mb-3">
                                    <div class="border p-2 rounded">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <strong class="rarity-${slot.getItem().getRarity().toString().toLowerCase()}">
                                                ${slot.getItem().getName()} <small> - ${slot.getItem().getLevelRequired()}</small>
                                            </strong>
                                            <div class="dropdown">
                                                <button class="btn btn-sm" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <i class="bi bi-three-dots"></i>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li>
                                                        <form action="/armory" method="post">
                                                            <input type="hidden" name="_method" value="equip">
                                                            <input type="hidden" name="itemName" value="${slot.getItem().getName()}">
                                                            <button type="submit" class="dropdown-item">Equip</button>
                                                        </form>
                                                    </li>
                                                    <li>
                                                        <form action="/armory" method="post">
                                                            <input type="hidden" name="_method" value="delete">
                                                            <input type="hidden" name="itemName" value="${slot.getItem().getName()}">
                                                            <button type="submit" class="dropdown-item text-danger">Delete</button>
                                                        </form>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <p>${slot.getItem().getDescription()}</p>
                                    </div>
                                </div>
                            @endif
                        @endfor
                        <div class="col-md-4 mb-3">
                            <a href="/items" class="border p-2 rounded bg-white d-flex justify-content-center align-items-center text-decoration-none">
                                <i class="bi bi-plus-circle fs-3"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a href="/home" class="btn btn-link">Back to home</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>