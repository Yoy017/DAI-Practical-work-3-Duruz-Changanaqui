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
    <p>Here you can see all the weapons and armors that you have in your inventory.</p>
    <div class="row">

        <!-- Display all items in the inventory -->
        @if (inventory.getSlots() != null)
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
                                <button class="btn btn-link" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="bi bi-three-dots"></i>
                                </button>
                                <ul class="dropdown-menu">
<%--                                    <li>--%>
<%--                                        <form action="/armory" method="put">--%>
<%--                                            <input type="hidden" name="itemName" value="${slot.getItem().getName()}">--%>
<%--                                            <button type="submit" class="dropdown-item">Update</button>--%>
<%--                                        </form>--%>
<%--                                    </li>--%>
                                    <li>
                                        <form action="/armory" method="post">
                                            <input type="hidden" name="_method" value="delete">
                                            <input type="hidden" name="itemName" value="${slot.getItem().getName()}">
                                            <button type="submit" class="dropdown-item text-danger">Delete</button>
                                        </form>
                                    </li>
                                    <li>
                                        <form action="/armory" method="post">
                                            <input type="hidden" name="_method" value="equip">
                                            <input type="hidden" name="itemName" value="${slot.getItem().getName()}">
                                            <button type="submit" class="dropdown-item">Equip</button>
                                        </form>
                                    </li>
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
        @endif
    </div>
    <div>
        <a href="/items" class="btn btn-primary"><i class="bi bi-plus"></i></a>
    </div>
</div>
<a href="/home" class="btn btn-link">Back to home</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
