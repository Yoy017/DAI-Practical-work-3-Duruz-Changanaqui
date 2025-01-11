@import ch.heigvd.dai.model.entity.Inventory
@import ch.heigvd.dai.model.entity.Item
@import ch.heigvd.dai.model.entity.Slot
@import ch.heigvd.dai.model.entity.SlotType
@import ch.heigvd.dai.model.entity.RarityType
@import java.util.LinkedList

@param Inventory inventory

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <title>Armory - Inventory</title>
</head>
<body class="bg-light">

<div class="container my-5">
    <h1 class="mb-4">Armory Page</h1>
    <p>Here you can see all the weapons and armors that you have in your inventory.</p>
    <div class="row">

        <!-- Display all items in the inventory -->
        @for (Slot slot : inventory.getSlots())
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h5 class="card-title">
                                <i class="@if (SlotType.EQUIPMENT.equals(slot.getItem().getType())) bi bi-shield @else bi bi-bag @endif me-2"></i>
                                ${slot.getItem().getName()}
                            </h5>
                            <div class="dropdown">
                                <button class="btn btn-link" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="bi bi-three-dots"></i>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item" href="#?name=${slot.getItem().getName()}?description=${slot.getItem().getDescription()}?levelRequired=${slot.getItem().getLevelRequired()}?rarity=${slot.getItem().getRarity().getType()}?quantity=${slot.getItem().getQuantity()}">Update</a></li>
                                    <li><a class="dropdown-item text-danger" href="#name=?${slot.getItem().getName()}">Delete</a></li>
                                </ul>
                            </div>
                        </div>
                        <p class="card-text">${slot.getItem().getDescription()}</p>
                        <p class="mb-1"><strong>Level Required:</strong> ${slot.getItem().getLevelRequired()}</p>
                        <p>
                            <strong>Rarity:</strong>
                            <span class="badge
                                @if (RarityType.COMMON.equals(slot.getItem().getRarity().getType())) bg-secondary
                                @elseif (RarityType.UNCOMMON.equals(slot.getItem().getRarity().getType())) bg-success
                                @elseif (RarityType.RARE.equals(slot.getItem().getRarity().getType())) bg-primary
                                @elseif (RarityType.EPIC.equals(slot.getItem().getRarity().getType())) bg-warning text-dark
                                @elseif (RarityType.LEGENDARY.equals(slot.getItem().getRarity().getType())) bg-danger
                                @endif">
                                ${slot.getItem().getRarity().toString()}
                            </span>
                        </p>
                    </div>
                </div>
            </div>
        @endfor

        <!-- Empty card for adding a new item -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm border-0">
                <div class="card-body d-flex justify-content-center align-items-center" style="height: 100%;">
                    <a href="/items" class="btn btn-outline-primary">Add New Item</a>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="/home" class="btn btn-link">Back to home</a>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>