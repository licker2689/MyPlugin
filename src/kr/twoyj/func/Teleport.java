package kr.twoyj.func;

import kr.twoyj.cmd.MPCommand;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.UUID;

import static kr.twoyj.cmd.MPCommand.*;

public class Teleport {
    private final MPCommand MPC = new MPCommand();

    public void teleportSpawn(Player p) {
        if (p.hasPermission("myplugin.teleport.lobby")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map1.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map2.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map3.put(playerUUID, survivalLocation);
            }

            Location Dest_Spawn = new Location(loc.get("spawn").getWorld(), loc.get("spawn").getX(), loc.get("spawn").getY(), loc.get("spawn").getZ()); //스폰
            p.teleport(Dest_Spawn);
            p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");

            gamemode_adventure(p);
        } else {
            MPC.no_permissions(p, "myplugin.teleport.lobby");
        }
    }

    public void teleportLobby(Player p) {
        if (p.hasPermission("myplugin.teleport.lobby")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map1.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map2.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map3.put(playerUUID, survivalLocation);
            }

            Location Dest_Lobby = new Location(loc.get("lobby").getWorld(), loc.get("lobby").getX(), loc.get("lobby").getY(), loc.get("lobby").getZ()); //로비
            p.teleport(Dest_Lobby);
            p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");

            gamemode_adventure(p);
        } else {
            MPC.no_permissions(p, "myplugin.teleport.lobby");
        }
    }

    public void teleportCasino(Player p) {
        if (p.hasPermission("myplugin.teleport.casino")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map1.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map2.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map3.put(playerUUID, survivalLocation);
            }

            Location Dest_Casino = new Location(loc.get("casino").getWorld(), loc.get("casino").getX(), loc.get("casino").getY(), loc.get("casino").getZ()); //카지노
            p.teleport(Dest_Casino);
            p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");

            gamemode_adventure(p);
        } else {
            MPC.no_permissions(p, "myplugin.teleport.casino");
        }
    }

    public void teleportMinigame1(Player p) {
        if (p.hasPermission("myplugin.teleport.minigame1")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map1.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map2.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map3.put(playerUUID, survivalLocation);
            }

            Location Dest_Minigame1 = new Location(loc.get("minigame1").getWorld(), loc.get("minigame1").getX(), loc.get("minigame1").getY(), loc.get("minigame1").getZ()); //미니게임1
            p.teleport(Dest_Minigame1);
            p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");

            gamemode_adventure(p);

        } else {
            MPC.no_permissions(p, "myplugin.teleport.minigame1");
        }
    }

    public void teleportSurvival1(Player p) {
        if (p.hasPermission("myplugin.teleport.survival")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                p.sendMessage(ChatColor.RED + "오류: 현재 " + ChatColor.GREEN + p.getName() + ChatColor.RED + "님은 이동하려는 월드와 같은 월드에 있으므로 이동을 취소했습니다.");
            } else {
                if (p.getWorld() == Bukkit.getWorld("surv2")) {
                    UUID playerUUID = p.getUniqueId();
                    map2.remove(playerUUID);
                    map2.put(playerUUID, p.getLocation());
                } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                    UUID playerUUID = p.getUniqueId();
                    map3.remove(playerUUID);
                    map3.put(playerUUID, p.getLocation());
                }

                Location Dest_surv1 = new Location(loc.get("surv1").getWorld(), loc.get("surv1").getX(), loc.get("surv1").getY(), loc.get("surv1").getZ()); //야생1
                p.teleport(Dest_surv1);
                p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");

                gamemode_survival(p);

                UUID playerUUID = p.getUniqueId();
                if (map1.containsKey(playerUUID)) {
                    p.teleport(map1.get(playerUUID));
                    map1.remove(playerUUID);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치로 텔레포트 됩니다.");
                } else {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치가 없습니다.");
                }
            }
        } else {
            MPC.no_permissions(p, "myplugin.teleport.survival");
        }
    }

    public void teleportSurvival2(Player p) {
        if (p.hasPermission("myplugin.teleport.survival")) {
            if (p.getWorld() == Bukkit.getWorld("surv2")) {
                p.sendMessage(ChatColor.RED + "오류: 현재 " + ChatColor.GREEN + p.getName() + ChatColor.RED + "님은 이동하려는 월드와 같은 월드에 있으므로 이동을 취소했습니다.");
            } else {
                if (p.getWorld() == Bukkit.getWorld("surv1")) {
                    UUID playerUUID = p.getUniqueId();
                    map1.remove(playerUUID);
                    map1.put(playerUUID, p.getLocation());
                } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                    UUID playerUUID = p.getUniqueId();
                    map3.remove(playerUUID);
                    map3.put(playerUUID, p.getLocation());
                }

                Location Dest_surv2 = new Location(loc.get("surv2").getWorld(), loc.get("surv2").getX(), loc.get("surv2").getY(), loc.get("surv2").getZ()); //야생2
                p.teleport(Dest_surv2);
                p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");

                gamemode_survival(p);

                UUID playerUUID = p.getUniqueId();
                if (map2.containsKey(playerUUID)) {
                    p.teleport(map2.get(playerUUID));
                    map2.remove(playerUUID);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치로 텔레포트 됩니다.");
                } else {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치가 없습니다.");
                }
            }
        } else {
            MPC.no_permissions(p, "myplugin.teleport.survival");
        }
    }

    public void teleportSurvival3(Player p) {
        if (p.hasPermission("myplugin.teleport.survival")) {
            if (p.getWorld() == Bukkit.getWorld("surv3")) {
                p.sendMessage(ChatColor.RED + "오류: 현재 " + ChatColor.GREEN + p.getName() + ChatColor.RED + "님은 이동하려는 월드와 같은 월드에 있으므로 이동을 취소했습니다.");
            } else {
                if (p.getWorld() == Bukkit.getWorld("surv1")) {
                    UUID playerUUID = p.getUniqueId();
                    map1.remove(playerUUID);
                    map1.put(playerUUID, p.getLocation());
                } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                    UUID playerUUID = p.getUniqueId();
                    map2.remove(playerUUID);
                    map2.put(playerUUID, p.getLocation());
                }

                Location Dest_surv3 = new Location(loc.get("surv3").getWorld(), loc.get("surv3").getX(), loc.get("surv3").getY(), loc.get("surv3").getZ()); //야생3
                p.teleport(Dest_surv3);
                p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");

                gamemode_survival(p);

                UUID playerUUID = p.getUniqueId();
                if (map3.containsKey(playerUUID)) {
                    p.teleport(map3.get(playerUUID));
                    map3.remove(playerUUID);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치로 텔레포트 됩니다.");
                } else {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치가 없습니다.");
                }
            }
        } else {
            MPC.no_permissions(p, "myplugin.teleport.survival");
        }
    }

    public void resetRecordLocatins(Player p) {
        UUID playerUUID = p.getUniqueId();

        if (map1.containsKey(playerUUID)) {
            map1.remove(playerUUID);
            p.sendMessage(ChatColor.GREEN + "야생1 | 좌표 정보를 성공적으로 삭제하였습니다!");
        } else {
            p.sendMessage(ChatColor.RED + "야생1 | 좌표 정보를 삭제하지 못했습니다!");
        }
        if (map2.containsKey(playerUUID)) {
            map2.remove(playerUUID);
            p.sendMessage(ChatColor.GREEN + "야생2 | 좌표 정보를 성공적으로 삭제하였습니다!");
        } else {
            p.sendMessage(ChatColor.RED + "야생2 | 좌표 정보를 삭제하지 못했습니다!");
        }
        if (map3.containsKey(playerUUID)) {
            map3.remove(playerUUID);
            p.sendMessage(ChatColor.GREEN + "야생3 | 좌표 정보를 성공적으로 삭제하였습니다!");
        } else {
            p.sendMessage(ChatColor.RED + "야생3 | 좌표 정보를 삭제하지 못했습니다!");
        }
    }

    private void gamemode_adventure(Player p) {

        if (!p.isOp()) {
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage(ChatColor.LIGHT_PURPLE + "어드벤쳐 모드로 변경되었습니다.");
            p.sendMessage(ChatColor.LIGHT_PURPLE + "현재 게임모드: " + ChatColor.WHITE + p.getGameMode());
        } else if (p.isOp()) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + "게임모드가 변경되지 않았습니다. 사유: 해당 플레이어는 OP를 소유하고 있습니다.");
        }
    }

    private void gamemode_survival(Player p) {
        if (!p.isOp()) {
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(ChatColor.LIGHT_PURPLE + "서바이벌 모드로 변경되었습니다.");
            p.sendMessage(ChatColor.LIGHT_PURPLE + "현재 게임모드: " + ChatColor.WHITE + p.getGameMode());
        } else if (p.isOp()) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + "게임모드가 변경되지 않았습니다. 사유: 해당 플레이어는 OP를 소유하고 있습니다.");
        }
    }
}
