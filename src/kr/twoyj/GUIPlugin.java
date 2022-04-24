package kr.twoyj;

import kr.twoyj.cmd.*;
import kr.twoyj.event.Event;
import kr.twoyj.gui.ItemInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

import static kr.twoyj.cmd.Home.home;
import static kr.twoyj.cmd.MPCommand.*;
import static kr.twoyj.cmd.Ticket.ticket;

public class GUIPlugin extends JavaPlugin implements CommandExecutor {
    private static GUIPlugin instance;

    Timer timerForAnnouncement = new Timer();
    int i = 0;

    @Override
    public void onEnable() {
        getLogger().info("[플러그인이 활성화됩니다]");

        getServer().getPluginManager().registerEvents(new Event(), this);
        getServer().getPluginManager().registerEvents(new ItemInventory(), this);
        getCommand("mp").setExecutor(new MPCommand());
        getCommand("rules").setExecutor(new Rules());
        getCommand("menu").setExecutor(new ItemCommands());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("home").setExecutor(new Home());
        getCommand("tk").setExecutor(new Ticket());

        instance = this;

        timerForAnnouncement.schedule(new TimerTask() {
            @Override
            public void run() {
                sendAnnoucement();
            }
        }, 5000, 120000);

        restoreMaps();
    }

    @Override
    public void onDisable() {
        getLogger().info("[플러그인이 비활성화됩니다]");

        timerForAnnouncement.cancel();
        saveMaps1();
        saveMaps2();
        saveMaps3();
        saveHome();
        saveTicket();
    }

    public static GUIPlugin getInstance() {
        return instance;
    }

    private void sendAnnoucement() {
        if (!(Bukkit.getOnlinePlayers().size() == 0)) {
            i++;
            switch (i) {
                case 1 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "핵, 엑스레이는 불법입니다.");
                case 2 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "디스코드 서버에 들어와주세요. http://discord.feathers.kro.kr");
                case 3 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "우리 서버를 추천해주세요. http://minelist.feathers.kro.kr");
                case 4 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "도메인이 mc.feather-s.kr로 변경되었습니다. 기존 도메인은 4/30에 지원 종료됩니다.");
                case 5 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "스태프를 모집합니다. http://forms.feathers.kro.kr");
                case 6 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "후원을 해주시면 각종 특권을 드립니다. http://thanks.feathers.kro.kr");
            }
            if (i == 6) {
                i = 0;
            }
        }
    }

    private void saveMaps1() {
        try {
            if (!(map1.size() == 0)) {
                getLogger().info("[map1 저장 시작]");
                for (Map.Entry<UUID, Location> entry : map1.entrySet()) {
                    getConfig().set("maps.map1." + entry.getKey(), entry.getValue());
                    getLogger().info("[map1 저장중 : " + entry.getKey() + "]");
                }
                saveConfig();
                getLogger().info("[map1 저장완료]");
            } else {
                getLogger().info("[map1 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[map1 저장 실패]");
            e.printStackTrace();
        }
    }

    private void saveMaps2() {
        try {
            if (!(map2.size() == 0)) {
                getLogger().info("[map2 저장 시작]");
                for (Map.Entry<UUID, Location> entry : map2.entrySet()) {
                    getConfig().set("maps.map2." + entry.getKey(), entry.getValue());
                    getLogger().info("[map2 저장중 : " + entry.getKey() + "]");
                }
                saveConfig();
                getLogger().info("[map2 저장완료]");
            } else {
                getLogger().info("[map2 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[map2 저장 실패]");
        }
    }

    private void saveMaps3() {
        try {
            if (!(map2.size() == 0)) {
                getLogger().info("[map3 저장 시작]");
                for (Map.Entry<UUID, Location> entry : map3.entrySet()) {
                    getConfig().set("maps.map3." + entry.getKey(), entry.getValue());
                    getLogger().info("[map3 저장중 : " + entry.getKey() + "]");
                }
                saveConfig();
                getLogger().info("[map3 저장완료]");
            } else {
                getLogger().info("[map3 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[map3 저장 실패]");
        }
    }

    private void saveHome() {
        try {
            if (!(home.size() == 0)) {
                getLogger().info("[home 저장 시작]");
                for (Map.Entry<UUID, Location> entry : home.entrySet()) {
                    getConfig().set("maps.home." + entry.getKey(), entry.getValue());
                    getLogger().info("[home 저장중 : " + entry.getKey() + "]");
                }
                saveConfig();
                getLogger().info("[home 저장완료]");
            } else {
                getLogger().info("[home 저장 취소");
            }
        } catch (Exception e) {
            getLogger().info("[home 저장 실패]");
        }
    }

    private void saveTicket() {
        try {
            if (!(ticket.size() == 0)) {
                getLogger().info("[ticket 저장 시작]");
                for (Map.Entry<UUID, Integer> entry : ticket.entrySet()) {
                    getConfig().set("maps.ticket." + entry.getKey(), entry.getValue());
                    getLogger().info("[ticket 저장중 : " + entry.getKey() + "]");
                }
                saveConfig();
                getLogger().info("[ticket 저장완료]");
            } else {
                getLogger().info("[ticket 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[ticket 저장 실패]");
        }
    }

    public void saveMaps() {
        saveMaps1();
        saveMaps2();
        saveMaps3();
        saveHome();
        saveTicket();
    }

    public void restoreMaps() {
        try {
            getLogger().info("[map1 불러오기 시작]");
            getConfig().getConfigurationSection("maps.map1").getKeys(false).forEach(key -> {
                Location content = ((Location) getConfig().get("maps.map1." + key));
                getLogger().info("[map1 불러오기 : " + key + "]");
                map1.put(UUID.fromString(key), content);
            });
            getLogger().info("[map1 불러오기 완료]");
        } catch (Exception e) {
            getLogger().info("[map1 불러오기 실패]");
            e.printStackTrace();
        }


        try {
            getLogger().info("[map2 불러오기 시작]");
            getConfig().getConfigurationSection("maps.map2").getKeys(false).forEach(key -> {
                Location content = ((Location) getConfig().get("maps.map2." + key));
                getLogger().info("[map2 불러오기 : " + key + "]");
                map2.put(UUID.fromString(key), content);
            });
            getLogger().info("[map2 불러오기 완료]");
        } catch (Exception e) {
            getLogger().info("[map2 불러오기 실패]");
            e.printStackTrace();
        }


        try {
            getLogger().info("[map3 불러오기 시작]");
            getConfig().getConfigurationSection("maps.map3").getKeys(false).forEach(key -> {
                Location content = ((Location) getConfig().get("maps.map3." + key));
                getLogger().info("[map3 불러오기 : " + key + "]");
                map3.put(UUID.fromString(key), content);
            });
            getLogger().info("[map3 불러오기 완료]");
        } catch (Exception e) {
            getLogger().info("[map3 불러오기 실패]");
            e.printStackTrace();
        }


        try {
            getLogger().info("[home 불러오기 시작]");
            getConfig().getConfigurationSection("maps.home").getKeys(false).forEach(key -> {
                Location content = ((Location) getConfig().get("maps.home." + key));
                getLogger().info("[home 불러오기 : " + key + "]");
                home.put(UUID.fromString(key), content);
            });
            getLogger().info("[home 불러오기 완료]");
        } catch (Exception e) {
            getLogger().info("[home 불러오기 실패]");
            e.printStackTrace();
        }


        if (getConfig().getConfigurationSection("maps.ticket").getKeys(false).isEmpty()) {
            getLogger().info("[ticket 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[ticket 불러오기 시작]");
            getConfig().getConfigurationSection("maps.ticket").getKeys(false).forEach(key -> {
                Integer content = (Integer.parseInt(getConfig().get("maps.ticket." + key).toString()));
                getLogger().info("[ticket 불러오기 : " + key + "]");
                ticket.put(UUID.fromString(key), content);
            });
            getLogger().info("[ticket 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.spawn").getKeys(false).isEmpty()) {
            getLogger().info("[spawn 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[spawn 불러오기 시작]");
            String world = getConfig().get("worlds.spawn.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.spawn.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.spawn.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.spawn.z").toString());
            Location loc_spawn = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("spawn", loc_spawn);
            getLogger().info("[spawn 불러오기 : " + loc_spawn + "]");
            getLogger().info("[spawn 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.lobby").getKeys(false).isEmpty()) {
            getLogger().info("[lobby 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[lobby 불러오기 시작]");
            String world = getConfig().get("worlds.lobby.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.lobby.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.lobby.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.lobby.z").toString());
            Location loc_lobby = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("lobby", loc_lobby);
            getLogger().info("[lobby 불러오기 : " + loc_lobby + "]");
            getLogger().info("[lobby 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.surv1").getKeys(false).isEmpty()) {
            getLogger().info("[surv1 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[surv1 불러오기 시작]");
            String world = getConfig().get("worlds.surv1.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.surv1.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.surv1.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.surv1.z").toString());
            Location loc_surv1 = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("surv1", loc_surv1);
            getLogger().info("[surv1 불러오기 : " + loc_surv1 + "]");
            getLogger().info("[surv1 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.surv2").getKeys(false).isEmpty()) {
            getLogger().info("[surv2 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[surv2 불러오기 시작]");
            String world = getConfig().get("worlds.surv2.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.surv2.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.surv2.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.surv2.z").toString());
            Location loc_surv2 = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("surv2", loc_surv2);
            getLogger().info("[surv2 불러오기 : " + loc_surv2 + "]");
            getLogger().info("[surv2 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.surv3").getKeys(false).isEmpty()) {
            getLogger().info("[surv3 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[surv3 불러오기 시작]");
            String world = getConfig().get("worlds.surv3.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.surv3.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.surv3.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.surv3.z").toString());
            Location loc_surv3 = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("surv3", loc_surv3);
            getLogger().info("[surv3 불러오기 : " + loc_surv3 + "]");
            getLogger().info("[surv3 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.casino").getKeys(false).isEmpty()) {
            getLogger().info("[casino 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[casino 불러오기 시작]");
            String world = getConfig().get("worlds.casino.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.casino.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.casino.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.casino.z").toString());
            Location loc_casino = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("casino", loc_casino);
            getLogger().info("[casino 불러오기 : " + loc_casino + "]");
            getLogger().info("[casino 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.minigame1").getKeys(false).isEmpty()) {
            getLogger().info("[minigame1 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[minigame1 불러오기 시작]");
            String world = getConfig().get("worlds.minigame1.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.minigame1.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.minigame1.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.minigame1.z").toString());
            Location loc_minigame1 = new Location(Bukkit.getWorld(world), x, y, z);
            loc.put("minigame1", loc_minigame1);
            getLogger().info("[minigame1 불러오기 : " + loc_minigame1 + "]");
            getLogger().info("[minigame1 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.zw").getKeys(false).isEmpty()) {
            getLogger().info("[zw 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[zw 불러오기 시작]");
            String world = getConfig().get("worlds.zw.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.zw.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.zw.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.zw.z").toString());
            Location loc_zw = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("zw", loc_zw);
            getLogger().info("[zw 불러오기 : " + loc_zw + "]");
            getLogger().info("[zw 불러오기 완료]");
        }


        if (getConfig().getConfigurationSection("worlds.zb").getKeys(false).isEmpty()) {
            getLogger().info("[zb 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[zb 불러오기 시작]");
            String world = getConfig().get("worlds.zb.world").toString();
            int x = Integer.parseInt(getConfig().get("worlds.zb.x").toString());
            int y = Integer.parseInt(getConfig().get("worlds.zb.y").toString());
            int z = Integer.parseInt(getConfig().get("worlds.zb.z").toString());
            Location loc_zb = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("zb", loc_zb);
            getLogger().info("[zb 불러오기 : " + loc_zb + "]");
            getLogger().info("[zb 불러오기 완료]");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("mpr")) {
            if (sender.isOp()) {
                restoreMaps();
                sender.sendMessage(ChatColor.GREEN + "[콘피그 파일 불러오기 완료]");
            } else {
                sender.sendMessage(ChatColor.RED + "오류: 이 기능은 OP 권한만 사용가능합니다.");
            }
        }
        else if (label.equalsIgnoreCase("mps")) {
            if (sender.isOp()) {
                saveMaps();
                sender.sendMessage(ChatColor.GREEN + "[콘피그 파일 저장 완료]");
            } else {
                sender.sendMessage(ChatColor.RED + "오류: 이 기능은 OP 권한만 사용가능합니다.");
            }
        }
        return false;
    }
}