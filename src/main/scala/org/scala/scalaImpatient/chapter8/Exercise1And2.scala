package org.scala.scalaImpatient.chapter8

//1. Extend the following BankAccount class to a CheckingAccount class that charges $1
//for every deposit and withdrawal.
//class BankAccount(initialBalance: Double) {
//  private var balance = initialBalance
//  def currentBalance = balance
//  def deposit(amount: Double) = { balance += amount; balance }
//  def withdraw(amount: Double) = { balance -= amount; balance }
//}

// 2. Extend the BankAccount class of the preceding exercise into a class SavingsAccount
//that earns interest every month (when a method earnMonthlyInterest is called)
//and has three free deposits or withdrawals every month. Reset the transaction
//count in the earnMonthlyInterest method.

object Exercise12 extends App {
  class BankAccount(initialBalance: Double) {
    private var balance = if (initialBalance >= 0.0) initialBalance else 0.0
    def currentBalance: Double = balance
    def deposit(amount: Double): Double = { balance += amount; balance }
    def withdraw(amount: Double): Double = { balance -= amount; balance }
  }

  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    override def deposit(amount: Double): Double = super.deposit(amount-1)
    override def withdraw(amount: Double): Double = {
      if (currentBalance > amount) {
        super.withdraw(amount - 1)
      }

      throw new IllegalStateException("Withdrawal amount exceeds the current balance")
    }
  }

  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    private val MaxFreeTransactions: Int = 3

    private[this] var transactionCounter: Int = 0

    private def isFreeTransaction: Boolean = transactionCounter < MaxFreeTransactions

    private def incrementTransaction(): Unit = this.transactionCounter += 1

    private def resetFreeTransaction(): Unit = this.transactionCounter = 0

    override def deposit(amount: Double): Double =  {
      val depositAmount = amount + (if (isFreeTransaction) 0.0 else -1.0)
      this.incrementTransaction()
      super.deposit(depositAmount)
    }

    override def withdraw(amount: Double): Double = {
      val withdrawalAmount = amount + (if (isFreeTransaction) 0.0 else -1.0)
      this.incrementTransaction()
      super.withdraw(withdrawalAmount)
    }

    // Using paranthesis because this method causes state change
    // by increasing the balance.
    def earnMonthlyInterest(): Double = {
      this.resetFreeTransaction()
      // Interest of 7.5 percent
      val interest = currentBalance * 7.5
      super.deposit(interest)
      interest
    }
  }
}
