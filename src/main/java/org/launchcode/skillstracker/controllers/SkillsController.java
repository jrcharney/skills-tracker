package org.launchcode.skillstracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SkillsController {

    public static final String TITLE = "Skills Tracker";
    public static final String STYLESHEET = "css/styles.css";

    // TODO: eventually move most of my HTML generating stuff to another file.
    public static final String BR = "<br>";
    public static final String HR = "<hr>";

    /**
     * @name pageHead
     * @description all the stuff that should go in the head section
     * @param title
     * @param stylesheet
     * @return
     */
    public static String pageHead(String title,String stylesheet){
        return String.join("\n",
                "<meta charset=\"UTF-8\">",     // Look at https://www.w3schools.com/tags/tag_meta.asp later
                "<title>"+title+"</title>",
                "<link rel=\"stylesheet\" href=\""+stylesheet+"\">"
        );
    }

    /**
     * @name pageHead
     * @description default page head
     * @return {String}
     */
    public static String pageHead(){
        return pageHead(TITLE,STYLESHEET);
    }

    /**
     * @name htmlDocument
     * @description Generate an HTML document
     * @param head {String} content of the head element
     * @param body {String} content of the body element
     * @return {String}
     */
    public static String htmlDocument(String head, String body){
        // Do I need a doctype? YES! Spring will NOT add one. You should use in if you are doing HTML5 which we are!
        return String.join("\n",
                "<!doctype html>",
                "<html>",
                "<head>", head, "</head>",
                "<body>", body, "</body>",
                "</html>"
        );
    }

    public static String htmlDocument(String body){
        return htmlDocument(pageHead(),body);
    }

    // TODO: create elements that should go in the head element (title, meta, style, script)

    /**
     * @name hx
     * @descripiton create a header element
     * @param level {int} header level between 1 and 6
     * @param text {String} contents of the header
     * @return {String}
     */
    public static String hx(int level, String text){
        return "<h"+level+">" + text + "</h"+level+">";
    }

    /**
     * @name unorderedList
     * @description Create an unordered list from an array of strings
     * @param items {String[]} an array of items
     * @return {String}
     */
    public static String unorderedList(String[] items){
        StringBuilder lis = new StringBuilder();
        for(String item : items){
            lis.append("<li>" + item + "</li>\n");
        }
        return String.join("\n","<ul>", lis.toString(),"</ul>");
    }

    /**
     * @name orderedList
     * @description Create an ordered list from an array of strings
     * @param items {String[]} an array of items
     * @return {String}
     */
    public static String orderedList(String[] items){
        StringBuilder lis = new StringBuilder();
        for(String item : items){
            lis.append("<li>" + item + "</li>\n");
        }
        return String.join("\n","<ol>", lis.toString(),"</ol>");
    }

    /**
     * @name selectMenu
     * @description Create a drop down select menu.
     * @param name {String} the name and id of the select menu
     * @param items {String[]} an array of items
     * @return {String}
     */
    public static String selectMenu(String name, String[] items){
        // TODO: Should I use ah HashMap instead of an Array of string? (Yes, but as another method)
        // If we use a Hashmap, the key will need to be option's value and the value would be the text.
        StringBuilder options = new StringBuilder();
        for(String item : items){
            options.append("<option>"+item+"</option>\n");
        }
        return String.join("\n","<select id=\""+name+"\" name=\""+name+"\">", options.toString(),"</select>");
    }

    /**
     * @name inputText
     * @description Create an input text field
     * @param name {String} the name and id of the input text field.
     * @param value {String} the value of the field. Initially blank.
     * @return {String}
     */
    public static String inputText(String name, String value){
        return "<input type=\"text\" id=\""+name+"\" name=\""+name+"\" value=\""+value+"\">";
    }

    public static String button(String name, String value){
        // NOTE: I probably won't use this method. Useful to have anyway.
        return "<input type=\"submit\" id=\""+name+"\" name=\""+name+"\" value=\""+value+"\">";
    }

    // TODO: eventually make methods for radio buttons, checkboxes, and ranges

    public static String submitButton(String value){
        return "<input type=\"submit\" value=\""+value+"\">";
    }

    /**
     * @name label
     * @description Create a label
     * @param text {String} The text of the label
     * @return {String}
     */
    public static String label(String text){
        return "<label>" + text + "</label>";
    }

    /**
     * @name label
     * @description Create a label with a for attribute to associate with another input element.
     * @param forItem {String} the value of the for attribute which should be the id of an input element.
     * @param text {String} The text of the label
     * @return {String}
     */
    public static String label(String forItem, String text){
        return "<label for=\""+forItem+"\">" + text + "</label>";
    }

    public static String skillsForm(){
        // we don't need to set a method value here since we want to use POST
        String[] languages = {"Bash","C++","Java","JavaScript","Python","TypeScript","Ruby","Perl"};

        String[] fields = new String[4];
        // first field is the name
        fields[0] = String.join("",
                label("coder","Name:"),
                inputText("coder","")
        );
        fields[1] = String.join("",
                label("skill0","Favorite programming language"),
                selectMenu("skill0",languages)
        );
        fields[2] = String.join("",
                label("skill1","Second favorite programming language"),
                selectMenu("skill1",languages)
        );
        fields[3] = String.join("",
                label("skill2","Third favorite programming language"),
                selectMenu("skill2",languages)
        );
        //String content = String.join(BR,fields);
        String form = String.join("\n",
                "<form method=\"post\" action=\"/form\">",
                String.join(BR,fields),
                BR,
                submitButton("Share!"),
                "</form>"
        );
        return form;
    }


    /* --------- */

    /**
     * @name home
     * @route "/"
     * @description The home page of this project
     * @return {String}
     */
    @GetMapping
    @ResponseBody
    public String home(){
        String h1 = hx(1,TITLE);
        //String h2 = hx(2,"We have a few skills we would like to learn. Her is the list!");
        /*
        String[] skills = new String[];
        skills[0] = "JavaScript";
        skills[1] = "TypeScript";
        skills[2] = "Java";
        String ol = orderedList(skills);
        */
        String body = String.join("\n",h1,HR,skillsForm());
        return htmlDocument(body);
    }

    /**
     * @name skillsTracker
     * @route "/form"
     * @description create a list from a form
     * @param coder
     * @param skill0
     * @param skill1
     * @param skill2
     * @return {String}
     * TODO: See if I can use fewer request parameters
     */
    @PostMapping("/form")
    @ResponseBody
    public String skillsTracker(@RequestParam String coder, @RequestParam String skill0, @RequestParam String skill1, @RequestParam String skill2){
        String h1 = hx(1,"Skills Tracker");
        String h2 = hx(2,coder);
        String[] skills = new String[3];
        skills[0] = skill0;
        skills[1] = skill1;
        skills[2] = skill2;
        String ol = orderedList(skills);
        String body = String.join("\n",h1,HR,h2,ol);
        return htmlDocument(body);
    }
}
