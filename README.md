---
title: The Vault Lab
type: lab
duration: "3:00"
creator: Brad Zimmerman (SEA)

---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) The Vault Lab

## Introduction

You have been recruited by a bank to hack into their secure server. From a cursory glance, you see that the bank is doing everything right. They are storing their SALTS (random string appended to passwords to make hashes more secure) in a secure map. They are using a slow hash algorithm to make sure that a malicious hacker such as yourself cannot guess too many passwords too quickly. The only thing it seems like they forgot to add is a check for a maximum number of guesses. This makes their server open to brute force attacks. You plan to exploit that fact.

## Exercise

The algorithm to break into the vault has already been provided for you from a previous engineer. It has some design flaws. They are as follows:

  - You can only break one password at a time. You could be breaking all of them concurrently to speed things up
  - Your lock views only change to green when all four passwords have been cracked. They need to change to green when their individual passwords have been cracked

Fix the design flaws and verify that your code increases the speed at which a password is cracked over the old algorithm.

#### Requirements

- Put each cracking process into an async task to run them at the same time.
- Use a handler to make sure the lock views are changed to green when an individual password is cracked.
- Return a time duration value after your async tasks are finished. Show the time difference between the old algorithm and the new one on the same passwords.

##### Bonus

- Crack a password with LENGTHADD = 4 by the time this class ends.

#### Deliverable

An app with all the requirements included
