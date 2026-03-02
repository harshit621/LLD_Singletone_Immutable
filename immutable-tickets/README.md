<<<<<<< HEAD
Exercise A — Singleton Refactoring (Metrics Registry)
----------------------------------------------------
Narrative
A CLI tool called **PulseMeter** collects runtime metrics (counters) and exposes them globally
so any part of the app can increment counters like `REQUESTS_TOTAL`, `DB_ERRORS`, etc.

The current implementation is **not a real singleton**, **not thread-safe**, and is vulnerable to
**reflection** and **serialization** breaking the singleton guarantee.

Your job is to refactor it into a **proper, thread-safe, lazy-initialized Singleton**.

What you have (Starter)
- `MetricsRegistry` is *intended* to be global, but:
  - `getInstance()` can return different objects under concurrency.
  - The constructor is not private.
  - Reflection can create multiple instances.
  - Serialization/deserialization can produce a new instance.
- `MetricsLoader` incorrectly uses `new MetricsRegistry()`.

Tasks
1) Make `MetricsRegistry` a proper, **thread-safe singleton**
   - **Lazy initialization**
   - **Private constructor**
   - Thread safety: pick one approach (recommended: static holder or double-checked locking)

2) Block reflection-based multiple construction
   - If the constructor is called when an instance already exists, throw an exception
   - (Hint: use a static flag/instance check inside the constructor)

3) Preserve singleton on serialization
   - Implement `readResolve()` so deserialization returns the same singleton instance

4) Update `MetricsLoader` to use the singleton
   - No `new MetricsRegistry()` anywhere in code

Acceptance
- Single instance across threads within a JVM run.
- Reflection cannot construct a second instance.
- Deserialization returns the same instance.
- Loading metrics from `metrics.properties` works.
- Values are accessible via:
  - `increment(key)`
  - `getCount(key)`
  - `getAll()`

Build/Run (Starter)
  cd singleton-metrics/src
  javac com/example/metrics/*.java
  java com.example.metrics.App

Useful Demo Commands (after you fix it)
- Concurrency check:
  java com.example.metrics.ConcurrencyCheck
- Reflection attack check:
  java com.example.metrics.ReflectionAttack
- Serialization check:
  java com.example.metrics.SerializationCheck

Note
This starter is intentionally broken. Some of these checks will "succeed" in breaking the singleton
until you fix the implementation.
=======
Exercise B — Immutable Classes (Incident Tickets)
------------------------------------------------
Narrative
A small CLI tool called **HelpLite** creates and manages support/incident tickets.
Today, `IncidentTicket` is **mutable**:
- multiple constructors
- public setters
- validation scattered across the codebase
- objects can be modified after being "created", causing audit/log inconsistencies

Refactor the design so `IncidentTicket` becomes **immutable** and is created using a **Builder**.

What you have (Starter)
- `IncidentTicket` has public setters + several constructors.
- `TicketService` creates a ticket, then mutates it later (bad).
- Validation is duplicated and scattered, making it easy to miss checks.
- `TryIt` demonstrates how the same object can change unexpectedly.

Tasks
1) Refactor `IncidentTicket` to an **immutable class**
   - private final fields
   - no setters
   - defensive copying for collections
   - safe getters (no internal state leakage)

2) Introduce `IncidentTicket.Builder`
   - Required: `id`, `reporterEmail`, `title`
   - Optional: `description`, `priority`, `tags`, `assigneeEmail`, `customerVisible`, `slaMinutes`, `source`
   - Builder should be fluent (`builder().id(...).title(...).build()`)

3) Centralize validation
   - Move ALL validation to `Builder.build()`
   - Use helpers in `Validation.java` (add more if needed)
   - Examples:
     - id: non-empty, length <= 20, only [A-Z0-9-] (you can reuse helper)
     - reporterEmail/assigneeEmail: must look like an email
     - title: non-empty, length <= 80
     - priority: one of LOW/MEDIUM/HIGH/CRITICAL
     - slaMinutes: if provided, must be between 5 and 7,200

4) Update `TicketService`
   - Stop mutating a ticket after creation
   - Any “updates” should create a **new** ticket instance (e.g., by Builder copy/from method)
   - Keep the API simple; you can add `toBuilder()` or `Builder.from(existing)`

Acceptance
- `IncidentTicket` has no public setters and fields are final.
- Tickets cannot be modified after creation (including tags list).
- Validation happens only in one place (`build()`).
- `TryIt` still works, but now demonstrates immutability (attempted mutations should not compile or have no effect).
- Code compiles and runs with the starter commands below.

Build/Run (Starter demo)
  cd immutable-tickets/src
  javac com/example/tickets/*.java TryIt.java
  java TryIt

Tip
After refactor, you can update `TryIt` to show:
- building a ticket
- “updating” by creating a new instance
- tags list is not mutable from outside
>>>>>>> 698d565 (Solution)
