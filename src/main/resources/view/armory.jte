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
        @for (Slot slot : inventory.getSlots())
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <h5 class="card-title">
                            <i class="@if (SlotType.EQUIPMENT.equals(slot.getItem().getType())) bi bi-shield @else bi bi-bag @endif me-2"></i>
                            ${slot.getItem().getName()}
                        </h5>
                        <p class="card-text">${slot.getItem().getDescription()}</p>
                        <p class="mb-1"><strong>Level Required:</strong> ${slot.getItem().getLevelRequired()}</p>
                        <p>
                            <strong>Rarity:</strong>
                            <span class="badge
                                    @switch (slot.getItem().getRarity()) {
                                        @case (RarityType.COMMON) bg-secondary
                                        @case (RarityType.UNCOMMON) bg-success
                                        @case (RarityType.RARE) bg-primary
                                        @case (RarityType.EPIC) bg-warning text-dark
                                        @case (RarityType.LEGENDARY) bg-danger
                                    }">
                                    ${slot.getItem().getRarity().toString()}
                                </span>
                        </p>
                    </div>
                </div>
            </div>
        @endfor
    </div>
</div>
    <a href="/home" class="btn btn-link">Back to home</a>

</body>
</html>