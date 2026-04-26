# 🤖 Cognia – Self Learning AI Chatbot

Cognia is a **Java-based Self Learning AI Chatbot** developed with a modern web interface.  
It is designed to simulate intelligent conversations by answering predefined questions, learning new responses from users, correcting wrong answers, and supporting **multiple chat sessions** similar to modern AI platforms like ChatGPT.

---

## 📌 Project Overview

Cognia is not just a basic chatbot. It combines:

- 🧠 **Self-learning capability**
- 💬 **Multi-chat conversation system**
- 🌐 **Modern web-based UI**
- 💾 **Persistent knowledge storage**
- 🔍 **Similarity-based question matching**
- ✏️ **Correction / update of learned answers**
- ⚡ **Real-time chat interaction**

The chatbot continuously grows smarter through user interactions.

---

## 🚀 Key Features

### 🧠 1. Self Learning Mechanism

If Cognia does not know the answer to a question, it responds:

> "I don't know the answer. Please teach me."

The user can then provide the answer, and the chatbot stores it permanently in `knowledge.txt`.

### Example:

**User:** Name a few fruits  
**Bot:** I don't know the answer. Please teach me.  
**User:** Apple, banana, mango, guava, etc.  
**Bot:** Thanks! I learned something new.

image.png

---

### 💬 2. Multiple Chat Support

Cognia supports multiple independent chat windows like ChatGPT.

- Create new chats
- Switch between chat sessions
- Separate message histories
- Better conversation organization

multi-chat.png

---

### 🔄 3. Answer Correction Feature

If the chatbot learns something incorrect, users can update it using:

- `actually`
- `correct answer is`
- `no`

### Example:

**User:** What is Java?  
**Bot:** Java is coffee.  

**User:** Actually Java is a programming language.  

**Bot:** Got it! I've updated the answer.

---

### 🔍 4. Smart Matching System

Cognia can understand similar questions using string similarity logic.

Examples:

- What is Java?
- Explain Java
- Tell me about Java

All can return similar answers.

---

### 🧠 5. Context Awareness

The chatbot remembers previous topics and supports follow-up terms like:

- it
- this
- that

Example:

**User:** What is Java?  
**User:** Who created it?

---

### 💾 6. Persistent Memory

All learned responses are stored locally in:

```text
data/knowledge.txt
