package me.gmpady.logmasterexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LogMasterExpansion extends PlaceholderExpansion {


    @Override
    public @NotNull String getIdentifier() {
        return "logmaster";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Gmpady";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean register() {
        return super.register();
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        String[] param = params.split("_");
        String logPlayer = param[1];
        if(param[0].equalsIgnoreCase("log")) {
            if (param.length == 3) {
                String logText = param[2];
                if (logText.length() == 0 || logText == "") {
                    System.out.println("[LogMaster] Error! Please write the text for the log!");
                    return "§c✖";
                }
                if(logPlayer.contains("/") || logPlayer.contains("\\")) {
                    return "§c✖";
                }
                createYamlFile("LogMaster", logPlayer, UTF8(logText
                        .replace("{date}", getCurrentDate())
                        .replace("{player}", logPlayer)));
                return "";
            }
        }
        else if(param[0].equalsIgnoreCase("get")){
            if(param.length == 2){
                if(logPlayer.contains("/") || logPlayer.contains("\\")) {
                    return "§c✖";
                }
                    return readYamlFile("LogMaster", logPlayer);
            }
        }
        return null;
    }

    public static void createYamlFile(String directoryName, String fileName, String content) {
        File directory = new File(directoryName);

        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!fileName.endsWith(".yml")) {
            fileName += ".yml";
        }

        String filePath = Paths.get(directoryName, UTF8(fileName)).toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(content);
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: \n" + e.getMessage());
        }
    }

    public static String readYamlFile(String directoryName, String fileName) {
        if (!fileName.endsWith(".yml")) {
            fileName += ".yml";
        }

        Path filePath = Paths.get(directoryName, UTF8(fileName));

        try {
            List<String> lines = Files.readAllLines(filePath);
            return String.join("\n", lines);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: \n" + e.getMessage());
            return null;
        }
    }

    public static String getCurrentDate(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

        return currentDateTime.format(formatter);
    }

    public static String UTF8(String text) {
        Pattern pattern = Pattern.compile("\\{unicode\\+([0-9A-Fa-f]{4,6})\\}");
        Matcher matcher = pattern.matcher(text);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String unicodeStr = matcher.group(1);

            int unicode = Integer.parseInt(unicodeStr, 16);
            String unicodeChar = new String(Character.toChars(unicode));

            matcher.appendReplacement(result, unicodeChar);
        }
        matcher.appendTail(result);

        return result.toString();
    }
}
