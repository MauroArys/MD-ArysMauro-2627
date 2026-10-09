val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)

val groupedEvents = events.groupBy { it.daypart }
groupedEvents.forEach { (daypart, events) ->
    println("$daypart: ${events.size} events")
}

println("Last event of the day: ${events.last().title}")

val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }