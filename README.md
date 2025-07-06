# DMRC Ticket Machine Clone

A JavaFX-based metro ticket machine simulator inspired by Delhi Metro's DMRC kiosks.

This is a desktop application simulating a DMRC Metro Station Ticket Machine, allowing users to:

-Book QR-based metro tickets
-Recharge metro cards
-View travel history
-See ticket summary with fare and distance calculation
-Interact via a modern, animated JavaFX GUI

---

### Technologies Used:

- Java 17 (Temurin JDK)
- JavaFX 21
- IntelliJ IDEA (Build System: IntelliJ / Gradle)
- Gradle for dependency management
- MySQL (for dynamic station data)
- (Optional) ZXing for QR Generation
- (Planned) SQLite for ticket & transaction storage

---

## Folder Structure
```
src/
└── main/
      ├── java/
      │   └── org/
      │       └── dmrc/
      │           ├── Main.java             <-- Application launcher & scene management
      │           ├── WelcomeScreen.java    <-- Welcome screen UI and logic
      │           └── BookTicketScreen.java <-- Book Ticket screen logic
      │           ├── Station.java          <-- Station model class
      │           └── StationDAO.java       <-- Data access object for MySQL station handling
      │ 
      └── resources/
      ├── style.css          <-- Custom CSS styling for modern look
      ├── config.properties  <-- Station name & terminal ID config
      └── subway.png         <-- DMRC metro logo/icon
```

---

## How to Run

1. Clone the repo
2. Open in IntelliJ
3. Make sure your MySQL database is set up and running (stations table created and filled)
4. Update DB config in `DBUtil.java` if needed
5. Ensure Java 17 & JavaFX SDK are configured
6. Run `Main.java`

---

## Features Completed:

- **JavaFX Project setup with Gradle & JDK 17**
- **Welcome Screen UI with:**
    - **Station name & Terminal ID** fetched dynamically from `config.properties`
    - Real-time clock
    - Top header with DMRC logo, title, station info, and language selector
    - Four modern buttons:
        - Book Ticket
        - Recharge Card
        - View History
        - Exit
    - Ticker (announcement bar) with scrolling bilingual news/messages
    - Modern 2x2 layout for buttons: Book Ticket, Recharge, History, Exit
    - Multi-language support toggle (English/Hindi)
    - Metro tips section updating dynamically
    - Animated transitions on startup (sliding, fading)
    - Hidden Admin Panel access (Ctrl+Alt+A or corner button)
    - Stable UI size on maximizing and switching screens

- **Book Ticket Screen**:
  - Alphabet-based filtering buttons to show stations starting with selected letter
  - Station list dynamically fetched from MySQL database (using `StationDAO`)
  - Scrollable station selection area with responsive design
  - Fare preview and continue button that updates after selection
  - Cancel button to return to welcome screen (styled in red)
  - Modern card-like design and consistent styling
  - Prepared to handle large numbers of stations (future-proof)

- **Database Integration:**
  - Created `stations` table with sample stations
  - Plan to group stations by line color (yellow, blue, green, etc.)
  - Fetching station list dynamically instead of hardcoding
  - `Station.java` model and `StationDAO.java` for DB access
  - Error handling and logging improvements

- **Admin Panel:**
  - Basic admin login window with username/password check
  - Hidden corner button and keyboard shortcut
---

## Planned / In Progress

- Ticket QR code generation and printing (using ZXing)
- Metro card recharge simulation with virtual balance and database updates
- Travel/ticket history screen with data fetched from DB
- SQLite integration for storing tickets, transactions, and user actions
- Advanced fare calculation logic based on distance or station line
- Show stations by line color or section when needed (design ready)

---

## Code Architecture

- Modular design: separate screens (`WelcomeScreen`, `BookTicketScreen`), DB handling (`StationDAO`), and model (`Station`)
- Dynamic station data: no hardcoded station list, everything from DB
- Shared CSS (`style.css`) for unified styling and animations
- Properties file (`config.properties`) to update station or terminal info without code changes

---

## Known Issues & Improvements

- MySQL connector dependency security warnings (we plan to update when a new stable version is available)
- Improve DB error message UX (e.g., dialogs instead of console logs)
- Add more real stations from DMRC lines into DB


---

## Author

**Tushar**  
MCA Student | Aspiring Full Stack Developer | Open to Learn

Feel free to suggest or fork! Contributions and feedback welcome.