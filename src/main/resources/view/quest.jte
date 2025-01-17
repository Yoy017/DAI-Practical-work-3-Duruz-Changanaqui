@import ch.heigvd.dai.model.entity.Quest
@import ch.heigvd.dai.model.entity.Player
@import java.util.List

@param Player player
@param List<Quest> followedQuests
@param List<Quest> availableQuests

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>${player.getName()}'s Quests Journal</title>
</head>
<body class="bg-light">
<div class="container my-5">
    <h1 class="text-center mb-4">${player.getName()}'s Quests Journal</h1>
    <div class="row">
        <!-- Column: Followed Quests -->
        <div class="col-md-6">
            <h2>Followed Quests</h2>
            <ul class="list-group">
                @if (followedQuests.isEmpty())
                    <li class="list-group-item">No quests are currently being followed.</li>
                @else
                    @for (Quest quest : followedQuests)
                        <li class="list-group-item">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <h5>${quest.getName()}</h5>
                                    <p>${quest.getDescription()}</p>
                                </div>
                                <span class="badge bg-primary">Level ${quest.getRequiredLevel()}</span>
                            </div>
                            <div class="mt-2">
                                <form action="/quest/abandon" method="post" class="d-inline">
                                    <input type="hidden" name="questName" value="${quest.getName()}">
                                    <button type="submit" class="btn btn-danger btn-sm">Abandon</button>
                                </form>
                                <form action="/quest/complete" method="post" class="d-inline">
                                    <input type="hidden" name="questName" value="${quest.getName()}">
                                    <button type="submit" class="btn btn-success btn-sm">Complete</button>
                                </form>
                            </div>
                        </li>
                    @endfor
                @endif
            </ul>
        </div>

        <!-- Column: Available Quests -->
        <div class="col-md-6">
            <h2>Available Quests</h2>
            <ul class="list-group">
                @if (availableQuests.isEmpty())
                    <li class="list-group-item">No quests available for now.</li>
                @else
                    @for (Quest quest : availableQuests)
                        <li class="list-group-item">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <h5>${quest.getName()}</h5>
                                    <p>${quest.getDescription()}</p>
                                </div>
                                @if (player.canAcceptQuest(quest))
                                    <form action="/quest/accept" method="post" class="ms-3">
                                        <input type="hidden" name="questName" value="${quest.getName()}">
                                        <button type="submit" class="btn btn-success btn-sm">Accept</button>
                                    </form>
                                @else
                                    <button class="btn btn-secondary btn-sm" disabled>Unavailable</button>
                                @endif
                            </div>
                            <small class="text-muted">Requires level ${quest.getRequiredLevel()}</small>
                            @if (quest.getRequiredQuest() != null)
                                <small class="text-danger d-block">Dependency: ${quest.getRequiredQuest().getName()}</small>
                            @endif
                        </li>
                    @endfor
                @endif
            </ul>
        </div>
    </div>
    <a href="/home" class="btn btn-link mt-4">Back to Home</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
