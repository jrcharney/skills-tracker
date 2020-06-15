# Skills Tracker

Chapter 10 Studio

Much like Chapter 9's introduction to Spring, which became [Chapter 10's exercise](https://github.com/jrcharney/hello-spring), we continue to learn Spring this way.

Creating a README.md file (this one!) helps because then that Gradle Sync icon appears to get the app setup.

## What are we doing here?

In this Studio, we open `org.launchcode.skillstracker` (inside `skills-tracker/src/main/java`) and a `controllers` subpackage and create `SkillsController`.

In `SkillsController` you will add several methods to accomplish the following:

1. At `localhost:8080`, add text that states the three possible programming languages someone could be working on. You need to have:
    * An `h1` with the title `Skills Tracker`
    * An `h2` with a message. (Though a `p` would be better.)
    * An `ol` containing three programming languages of your choosing.
2. At `localhost:8080/form`, add a form that lets the user enter their name and choose their top three favorite programming languages on your list.
3. Finally, at `localhost:8080/form` use `@PostMapping` and `@RequestParam` to update the HTML with an `h1` element stating the user's name as well as an `ol` showing the three programming languages they picked in the order they were chosen.


## Final results

Not sure if I did everything right, but I am pleased with the results.

I even got CSS working in the static assets, though I'm sure we'll deal with that later in Thymeleaf.

## TODO
* [ ] Go back and replace the arrays with `ArrayList`s! (Good golly, I'm an idiot sometimes.)