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

---

## How to Run

1. Clone the repo
2. Open in IntelliJ
3. Ensure Java 17 & JavaFX SDK are configured
4. Run `Main.java`

---

## Planned Features

- [x] Welcome Screen UI
- [x] Modern UI Styling using CSS
- [x] Dynamic Station Name & Terminal ID using `config.properties`
- [ ] Station selection & fare calculator
- [ ] Ticket generation with QR
- [ ] Card recharge simulation
- [ ] Data persistence using SQLite

---

## Features Completed:
- JavaFX Project setup with Gradle & JDK 17
- Welcome UI includes:
    - **Station name & Terminal ID** fetched dynamically from `config.properties`
    - Real-time clock displayed at the bottom
    - Top header with:
        - DMRC Welcome message (left)
        - Station info (right)
    - Four modern buttons in 2x2 layout:
        - Book Ticket
        - Recharge Card
        - View History
        - Exit
    - Fully styled using `style.css`
    - Ticker (News Bar) with bilingual messages (English & Hindi)
    - Metro line animated illustration with current station highlight
    - Multi-language support toggle (English/Hindi)
    - Hidden Admin Panel access (Ctrl+Alt+A or corner button)

---

## Next Steps:
- Implement **Book Ticket screen** with:
    - Source & destination dropdown
    - Fare calculation
    - Ticket preview & QR generation

- Add screens for:
    - Recharge functionality
    - View travel history

- Enhance UI with:
    - Icons
    - Animated transitions
    - Custom styling themes

---

## Author
**Tushar**  
MCA Student | Aspiring Full Stack Developer | Open to Learn