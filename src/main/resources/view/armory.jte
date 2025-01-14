@import ch.heigvd.dai.model.entity.Inventory
@import ch.heigvd.dai.model.entity.Slot
@import ch.heigvd.dai.model.entity.SlotType
@import ch.heigvd.dai.model.entity.RarityType

@param Inventory inventory

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <title>Armory - Inventory</title>
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

        <!-- Equipped Items Section -->
        <div class="col-md-8 mb-4">
            <div class="card shadow-sm border-0">
                <div class="card-body">
                    <h5 class="card-title">Equipped Items</h5>
                    <div class="row">
                        @for (Slot slot : inventory.getSlots())
                            @if (SlotType.EQUIPMENT.equals(slot.getItem().getType()))
                                <div class="col-md-6 mb-3">
                                    <div class="border p-2 rounded bg-light">
                                        <strong>${slot.getItem().getName()}</strong>
                                        <p>${slot.getItem().getDescription()}</p>
                                    </div>
                                </div>
                            @endif
                        @endfor
                        <div class="col-md-6 mb-3">
                            <a href="/items" class="border p-2 rounded bg-light d-flex justify-content-center align-items-center text-decoration-none">
                                <i class="bi bi-plus-circle fs-3"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Inventory Section -->
            <div class="card mt-4 shadow-sm border-0">
                <div class="card-body">
                    <h5 class="card-title">Inventory</h5>
                    <div class="row">
                        @for (Slot slot : inventory.getSlots())
                            @if (!SlotType.EQUIPMENT.equals(slot.getItem().getType()))
                                <div class="col-md-4 mb-3">
                                    <div class="border p-2 rounded bg-white">
                                        <strong>${slot.getItem().getName()}</strong>
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