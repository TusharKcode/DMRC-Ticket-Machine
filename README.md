# DMRC Ticket Machine Clone

A JavaFX-based metro ticket machine simulator inspired by Delhi Metro's DMRC kiosks.

This is a JavaFX-based desktop application simulating the functionality of a **DMRC Metro Station Ticket Machine**.  
The app provides a graphical user interface for users to:
- Book QR-based metro tickets
- Metro Card Recharge
- View Ticket History
- Ticket summary with fare calculation
- Interactive GUI using JavaFX

---

### Technologies Used:
- Java 17 (Temurin JDK)
- JavaFX 21
- IntelliJ IDEA (Build System: IntelliJ / Gradle)
- Gradle for dependency management
- (Optional) ZXing for QR Generation
- (Optional) SQLite for Data Storage

---

## Folder Structure
src/
├── main/
│ ├── java/
│ │ └── org/
│ │     └── dmrc/
│ │         └── Main.java <-- Welcome screen UI
│
├── resources/
│ ├── style.css <-- Custom styling for modern look
│ └── config.properties <-- Station name & terminal ID config
│ └── subway.png <-- DMRC metro logo/icon
---

## How to Run

1. Clone the repo
2. Open in IntelliJ
3. Ensure Java 17 & JavaFX SDK are configured
4. Run `Main.java`

---

## Planned Features

- [ ] Ticket generation with QR
- [ ] Card recharge simulation (with balance updates)
- [ ] View travel/ticket history screen
- [ ] Data persistence using SQLite for transactions

---

## Features Completed:
- JavaFX Project setup with Gradle & JDK 17
- Welcome Screen UI with:
    - **Station name & Terminal ID** fetched dynamically from `config.properties`
    - Real-time clock displayed at the bottom
    - Top header with DMRC logo, title, station info, and language selector
    - Four modern buttons in 2x2 layout:
        - Book Ticket
        - Recharge Card
        - View History
        - Exit
    - Fully styled using `style.css`
    - Ticker (announcement bar) with scrolling bilingual news/messages
    - Metro line animated illustration with current station highlight
    - Modern 2x2 layout for buttons: Book Ticket, Recharge, History, Exit
    - Multi-language support toggle (English/Hindi)
    - Metro tips section updating dynamically
    - Animated transitions on startup
    - Hidden Admin Panel access (Ctrl+Alt+A or corner button)

- **Book Ticket Screen**:
    - Reuses header and clock for consistency
    - From and To station selection (combo boxes)
    - Dynamic fare and distance calculation
    - Modern, card-like layout with clean styling
    - Smooth transition back to welcome screen with stable stage size
- Modular CSS styling (`style.css`) for unified design
- Basic admin login window (username & password check)
---

## Improvements in Progress

- Further enhance button animations and transitions
- Add symbolic metro line illustrations or mini maps
- Refine admin panel functionality and diagnostics

---

## Author
**Tushar**  
MCA Student | Aspiring Full Stack Developer | Open to Learn

Feel free to suggest or fork! Contributions and feedback welcome.