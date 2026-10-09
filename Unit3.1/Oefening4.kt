val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)

val shortEvents = events.filter { it.durationInMinutes < 60 }
println("You have ${shortEvents.size} short events.")