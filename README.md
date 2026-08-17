# ArenaRunner

> A work-in-progress top-down action roguelite built with Java and libGDX.

ArenaRunner is an independent game development project focused on building a solid gameplay foundation while progressively exploring combat, player progression, enemy AI, and game systems.

The project is currently in active development and is being built from scratch as both a personal learning project and a portfolio piece.

## 🎮 Project Overview

The current concept is a **steampunk-inspired action roguelite** where the player fights through a series of floors, gradually becoming stronger through equipment and abilities.

The project is planned around a tower structure, where each floor represents a different combat environment.

The long-term goal is to develop **two playable towers with multiple floors**, while keeping the initial scope realistic enough to complete as a solo developer.

## 🛠️ Technologies

* **Java**
* **libGDX**
* **Gradle**
* **IntelliJ IDEA**
* **Git / GitHub**

## ⚔️ Current Features

### Player

* WASD movement
* Player state machine
* Idle and movement states
* Roll mechanic
* Roll invulnerability frames (i-frames)
* Direction-based rolling
* HP system
* Stamina system in development
* Basic player combat

### Enemy

* Enemy state machine
* Player tracking
* Attack range detection
* Attack wind-up
* Attack execution
* Attack recovery
* Damage system
* Death state
* Delayed respawn
* Randomized respawn position
* Basic boundary collision

### Combat

The current combat system is being designed around readable enemy attacks and player reactions.

Enemy attacks follow a state-based sequence:

```text
CHASE
   ↓
WINDUP
   ↓
ATTACK
   ↓
RECOVERY
   ↓
CHASE
```

This system is intended to give the player enough visual feedback to react to attacks using movement and the roll mechanic.

## 🧠 Development Approach

One of the main goals of this project is to build the game systems progressively rather than focusing on visual polish too early.

The current development priorities are:

1. Core gameplay
2. Player movement
3. Combat mechanics
4. Enemy AI
5. Progression systems
6. Level design
7. Visual polish
8. Animation and effects

Temporary shapes and placeholder assets are intentionally being used during early development to validate gameplay mechanics before investing heavily in final art and animation.

## 🌳 Planned Systems

Some of the systems planned for future development include:

* Dash
* Stamina management
* Weapons
* Skills and abilities
* Skill tree
* Equipment and weight system
* Different enemy types
* Boss encounters
* Multiple floors
* Tower progression
* Improved combat and hitboxes
* Pixel art animations
* Visual effects
* Audio
* Menus and additional UI

The roll mechanic is planned to interact with the player's equipment weight. Lighter builds will have faster and longer rolls, while heavier builds will sacrifice mobility for increased protection and equipment strength.

## 🎨 Art Direction

The current visual direction is inspired by **dark steampunk and medieval mechanical aesthetics**.

The player is being designed around a mechanical armored warrior, while enemies are planned to feature a mixture of undead, mechanical, and biomechanical elements.

Final artwork and animations are still subject to change as the gameplay systems evolve.

## 📈 Development Roadmap

The project is planned as a **4–6 month development project**, with progress being documented throughout development.

### Month 1 — Core Foundation

* [x] Player movement
* [x] Basic enemy
* [x] Player and enemy HP
* [x] Basic damage system
* [x] Death and respawn
* [x] Basic arena boundaries

### Month 2 — Movement & Combat Foundation

* [x] Player state machine
* [x] Roll with i-frames
* [x] Enemy state machine
* [x] Enemy attack wind-up
* [x] Attack and recovery states
* [ ] Stamina system
* [ ] Roll stamina consumption
* [ ] Dash
* [ ] Improved combat system

### Future Development

* [ ] Skills and abilities
* [ ] Skill tree
* [ ] Equipment system
* [ ] Weight-based movement
* [ ] Additional enemy types
* [ ] Boss
* [ ] Tower floors
* [ ] Pixel art and animations
* [ ] Audio
* [ ] Final UI and polish

## 📂 Project Structure

The project is organized into separate packages according to their responsibilities:

```text
com.renan.jogo
├── entity
│   ├── Player
│   └── Enemy
│
├── state
│   ├── PlayerState
│   └── EnemyState
│
├── ui
│   └── HUD
│
└── Main
```

This structure is expected to evolve as new systems are introduced.

## 🚧 Project Status

**In active development.**

ArenaRunner is currently focused on establishing its core gameplay systems. Many mechanics, visuals, and design decisions are still experimental and may change during development.

The repository is intentionally kept up to date to document the project's progress and development decisions.

## 🎯 Project Goals

Beyond creating a playable game, this project is being used to improve practical experience with:

* Object-oriented programming
* Game architecture
* State machines
* Game loops
* Collision detection
* Enemy AI
* Combat systems
* Resource management
* Git version control
* Software project organization
* Iterative game development

---

**ArenaRunner is a personal project currently under development.**
