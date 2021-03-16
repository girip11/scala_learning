package org.scala.rockthejvm.basic.patternMatching

// scalastyle:off regex.println

// References:
// https://data-flair.training/blogs/scala-regex/
// Regex symbol references from
// https://www3.ntu.edu.sg/home/ehchua/programming/howto/Regexe.html
import scala.util.matching.Regex

object RegexTryouts extends App {
  //Regex example

  val pattern: Regex = "[hH]ello".r
  // To find all the occurrences of hello or Hello in the given string
  println(pattern.findAllIn("Hello world, hello to all").mkString(","))

  // Regex for matching a content from the start to end
  // (i.e)whole string matching
  // https://stackoverflow.com/questions/4250062/what-is-the-difference-between-and-a-and-z-in-regex
  // we use ^(start of the line) and $(end of the line) for
  // matching an entire text in single line mode
  // \\A(start of the string) and \\Z(end of the string) similar
  // to ^ and $ in "multiline context".
  // [...] - matches a single character present inside the square brackets.
  // [^...] - negation of the above matching. Matches any character except
  // those inside the square brackets

  // Single line mode vs Multi line mode

  // (?s) refers to single line mode. \n is treated like a data.
  // Here end of data is the delimiter
  // (?m) refers to multiline mode. Think of \n is being treated as a delimiter
  // Reference:
  // https://stackoverflow.com/questions/17128158/multiline-regex-capture-in-scala/17128201
  // In multiline mode ^ and $ work for each line in the text, while in single line mode
  // they work on the whole text till the last new line character.

  import scala.compat.Platform.EOL
  var src = "12345" + "\n" + "abcdef" + EOL + "ABCDEF" + EOL //+ "-(*^4ad"

  // Ex-1
  var singleLineWholeTextPatternPerLine = "(?s)^[A-Za-z0-9]+$".r

  // This will try to match the whole src as single line while considering \n also
  // as a valid character. So there "won't" be any match
  println(singleLineWholeTextPatternPerLine.findFirstIn(src))

  //Ex-2
  // If I add \n as a matchable character to the [..]
  // then we will have the whole text matched
  singleLineWholeTextPatternPerLine = "(?s)^[A-Za-z0-9\\n]+$".r
  println(singleLineWholeTextPatternPerLine.findFirstIn(src))

  //=================================================================================

  // Multi line mode same example
  src = "12345" + EOL + "abcdef" + EOL + "ABCDEF" + EOL + "-(*^4ad"

  //Ex-3
  val multiLinePatternPerLine = "(?m)^[A-Za-z0-9]+$".r

  // Matches from start of every line to end of every line
  // note that we don't match the last line "-(*^4ad"
  println(multiLinePatternPerLine.findAllIn(src).mkString(","))

  // If we would want to treat the multiline text as a single subject
  // then we use \\A and \\Z(to exclude last \n) and \\z(includes last \n)
  // Ex-4
  val multiLineAsSingleTextPattern = "(?m)\\A[A-Za-z0-9\\n]+\\z".r
  // This won't match because of the "-(*^4ad"
  println(multiLineAsSingleTextPattern.findFirstIn(src))

  // This would match the entire text
  println(multiLineAsSingleTextPattern.findFirstIn(src.replace("-(*^4ad", "")))

  // So far we have seen matching
  // 1. start and end of line,
  // 2. start and end of entire text
  // Now we will see the start and end of a word
  // placing a regex between \b<something>\b
  // This is for marking the boundary of a word
  // NOTE: \\b is considered as backspace character when inside brackets
  // As you can see the regex will not match "Catalog"
  // since we don't reach word boundary after the match
  println("\\b(c|C)ats?\\b".r.findAllIn("cat cats Cat Cats catse Catalog").mkString(","))

  // ======================================================================================
  // dot character - matches any single character including new line in ?s mode
  // excluding the new line in ?m

  // whole text will be a single match in ?s mode since . will match \n as well
  println("Match for \"(?s).+\" -->", "(?s).+".r.findAllIn(src).mkString(","))

  // proof of . not matching \n in multiline mode
  // if . matched \n, then whole text should be matched once
  println("(?m).+".r.findAllIn(src).mkString(","))

  //=============================================================================

  // re* - preceding expression should be present 0 or more times (>= 0)
  // re+ - preceding expression should be present atleast once (>= 1)
  // re? - preceding expression should be present atmost once (0 or 1)
  // re {n} - preceding expression should occur exactly n times
  // re {n, } - preceding expression should occur n or more times ( >=n )
  // re {n,m} - preceding expression can occur atleast n times and atmost m times ( >=n and <= m )
  // a|b = either match a or b
  // if you have a lot of values to match a single character then you can go for [..]
  // Range values go well with [...]

  // Will match everything except Brock
  println("Br?o*ks+".r.findAllIn("Books Brooks Brokss Brock Bks").mkString(","))

  println("(B|b)o{2}ks?".r.findAllIn("Books books book Book boks boook").mkString(","))

  //match Indian mobile phone number
  println("[6-9][0-9]{9}".r.findAllIn("1234567890 9876543210 8876543210").mkString(","))

  //atleast and atmost usage example
  //match words containing e continuously between 1 and 3 times
  println(
    "[A-Za-z]*(E|e){1,3}(\\w)*".r
      .findAllIn("EEzy eerie007 between2 haloween amazing")
      .mkString(","))

  //=================================================================================
  // Remember these match exactly a single character
  // \\w - word characters - This will match a single character only. same as [a-zA-Z0-9_]
  // \\W - non-word characters. same as [^a-zA-Z0-9_]

  val sampleCodeSnippet =
    """
      |var _name = "John Doe"
      |var Age = 25
      |var student_1 = Student(_name, Age)
      |""".stripMargin

  //This will extract all variable names from the code
  println(
    "(\\w+)\\s*=".r
      .findAllMatchIn(sampleCodeSnippet)
      .toList
      .map(_.group(1))
      .mkString(","))

  // \\d - digits same as[0-9]
  // \\D - non digits. same as [^0-9]
  println(
    "[0-9]+".r.findAllIn("India won the 1983 and 2011 cricket world cup").mkString(","))
  println(
    "\\d+".r.findAllIn("India won the 1983 and 2011 cricket world cup").mkString(","))
  println(
    """\d+""".r.findAllIn("India won the 1983 and 2011 cricket world cup").mkString(","))

  // Negation
  println(
    "\\D+".r.findAllIn("India won the 1983 and 2011 cricket world cup").mkString(","))

  // \\s - all whitespace characters match [\t\r\n\f]
  // \\n, \\t - matches \n and \t respectively
  // \\S - non whitespace match
  // Extract till the url part
  // otherwise we have to explicitly mention matching of whitespace characters
  val sampleHtml =
    """hello <img
     alt="The dinosaur image"
     src="https://mdn.mozillademos.org/files/12708/image-with-title.png"
     style="display: block; height: 341px; margin: 0px auto; width: 400px;">"""

  println(
    "<img\\s+.*\\s+src=[\"']([^\"']+)[\\s\\S]+>".r
      .findFirstMatchIn(sampleHtml)
      .get
      .group(1))

  println("(?s)<img.*src=[\"']([^\"']+).*>".r.findFirstMatchIn(sampleHtml).get.group(1))

  // \\Q and \\E - Escape characters between \Q and \E to be taken for exact match(literal string)
  // instead of interpreting them as regex. When you have a string to match
  // and if it contains special regex characters you put the string inside
  // \Q and \E to match them literally.
  // Reference: https://stackoverflow.com/questions/30776860/regular-expression-q-e

  //Extract bold text from inside markdown
  //We know * has special meaning in regex. To make * as a value to look
  // for in the text we need to escape it
  println(
    "\\Q**\\E(\\w+)\\Q**\\E".r
      .findFirstMatchIn(
        """
          | **Markdown** language
          |""".stripMargin
      )
      .get
      .group(1))

  //================================================================================
  // Regex groups
  // expressions with in paranthesis () form a regex group
  // (re) - this is a regex group. The matched value is remembered and can be extracted
  // from Regex.Match groups
  // (?: re) - This also represents a regex group but this will be forgotten
  // This comes handy in cases where we want to enclose some text inside paranthesis
  // but that group should not be stored/remembered

  // This will match either K or Cite
  println("K|Cite".r.findAllIn("Kate Cite Kite Sight").mkString(","))

  // suppose we want to use (K|C) but we don't want it to be a group
  // that will be remember, then We want to do (K|C) or [KC]
  var matches = "(K|C)ite".r.findAllMatchIn("Kate Cite Kite Sight").toList
  println(matches.map(_.matched).mkString(","))
  //As we can see it remembers the K and C as groups in the matching
  println(matches.map(_.group(1)).mkString(","))

  matches = "(?:K|C)ite".r.findAllMatchIn("Kate Cite Kite Sight").toList
  println(matches.map(_.matched).mkString(","))
  //Here No groups are remembered and we can confirm the same from the group count
  // which will be 0 in this case
  println(matches.map(_.groupCount).mkString(","))

  // Regex greediness and backtracking
  //=================================
  // By default the regex match is a greedy one tries to find the longest match possible.
  // Repetition Operators *, +, ?, {m,n} are greedy operators
  // One trying to find the longest match possible it might have to backtrack
  // to get to the longest but correct match
  // Example : ".+b".r.findAllIn("aaaaaabcdb") will match the entire string
  // Reference:
  // https://stackoverflow.com/questions/9011592/
  // in-regular-expressions-what-is-a-backtracking-back-referencing

  // Curbing greediness with ?
  println(".+b".r.findAllIn("aaaaaabcdb").toList)

  // If we have to keep the match to the shortest first possible match
  // which is curbing the greediness then we can use ? following
  // *, +, ?, {m,} and {m,n} as in *?, +?, ??, {m,n}?, {m,}?
  println(".+?b".r.findAllIn("aaaaaabcdb").toList)

  // Curbing the backtracking with +
  //Backtracking: If a regex reaches a state where a match cannot be completed,
  // it backtracks by unwinding one character from the greedy match. For example,
  // if the regex z*zzz is matched against the string "zzzz", the z* first matches
  // "zzzz"; unwinds to match "zzz"; unwinds to match "zz"; and finally unwinds
  // to match "z", such that the rest of the patterns can find a match.

  //Possessive Quantifiers *+, ++, ?+, {m,n}+, {m,}+: You can put an extra + to the
  // repetition operators to disable backtracking, even it may result in
  // match failure. e.g, z++z will not match "zzzz". This feature might
  // not be supported in some languages.
  //This will still not match because no z will be found to match the last z in the regex
  println("z++z".r.findAllIn("zzzzzzzzzzzzzzzzzzzzzzz").toList)

  //==========================================================================================
  // I couldn't figure the uses of ?> and \\G in scala Regex. Everything else is covered
  // \\G - point where last match ended
  // (?>re) - Independent pattern. Doesn't backtrack
}
