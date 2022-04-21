package kr.twoyj.cmd;

import kr.twoyj.GUIPlugin;
import kr.twoyj.func.Teleport;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class MPCommand implements CommandExecutor, TabCompleter {
    /*
    surv1, 144, 63, 118
    surv2, 128, 71, 16
    surv3, -224, 105, -160
    world, 0, 70, 0
    casino, 3, -49, 26
    minigame1, 403, 116, -320
    co_01, 46, 52, -19
    minigame1, 0, -60, 0
    */

    private final Teleport TP = new Teleport();
    private final GUIPlugin GP = new GUIPlugin();

    public static Map<UUID, Location> map1 = new HashMap<>();
    public static Map<UUID, Location> map2 = new HashMap<>();
    public static Map<UUID, Location> map3 = new HashMap<>();
    public static Map<String, Location> loc = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "오류: 사용법이 잘못되었습니다. /mp help를 참고하세요.");
        }
        else if (args[0].equalsIgnoreCase("about")) {
            if (sender.hasPermission("myplugin.about")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "이 기능은 GUI메뉴의 커맨드 기능입니다.");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "[문의] 디스코드 : 호예준#3840");
                return true;
            } //mp about
            else {
                no_permissions((Player) sender, "myplugin.about");
                return false;
            }
        } //mp about
        else if (args[0].equalsIgnoreCase("tp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
                return false;
            } //콘솔등에서 입력시
            Player p = (Player) sender;

            if (p.hasPermission("myplugin.teleport")) {
                if (args[1].length() == 0) {
                    p.sendMessage(ChatColor.RED + "오류: 텔레포트 목적지가 설정되지 않았습니다.");
                    return false;
                } //mp tp
                else if (args[1].equalsIgnoreCase("survival1")) {
                    TP.teleportSurvival1(p);
                    return true;
                } //mp tp survival1
                else if (args[1].equalsIgnoreCase("survival2")) {
                    TP.teleportSurvival2(p);
                    return true;
                } //mp tp survival2
                else if (args[1].equalsIgnoreCase("survival3")) {
                    TP.teleportSurvival3(p);
                    return true;
                } //mp tp survival3
                else if (args[1].equalsIgnoreCase("lobby")) {
                    TP.teleportLobby(p);
                    return true;
                } //mp tp lobby
                else if (args[1].equalsIgnoreCase("spawn")) {
                    TP.teleportSpawn(p);
                    return true;
                } //mp tp spawn
                else if (args[1].equalsIgnoreCase("casino")) {
                    TP.teleportCasino(p);
                    return true;
                } //mp tp casino
                else if (args[1].equalsIgnoreCase("minigame1")) {
                    TP.teleportMinigame1(p);
                    return true;
                } //mp tp minigame1
                else if (args[1].equalsIgnoreCase("zw")) {
                    if (p.isOp()) {
                        Location Dest_zw = new Location(loc.get("zw").getWorld(), loc.get("zw").getX(), loc.get("zw").getY(), loc.get("zw").getZ()); //ZW
                        p.teleport(Dest_zw);
                        p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");
                        p.sendTitle("난장판 월드 입장!", "마음껏 노세요!", 4, 3, 2);
                        return true;
                    } else {
                        no_ops(p);
                        return false;
                    }
                } //mp tp zw
                else if (args[1].equalsIgnoreCase("zb")) {
                    if (p.isOp()) {
                        if (!args[2].isEmpty()) {
                            Player ForkPlayer = Bukkit.getPlayer(args[2]);
                            Location Dest_zb = new Location(loc.get("zb").getWorld(), loc.get("zb").getX(), loc.get("zb").getY(), loc.get("zb").getZ()); //ZB
                            if (ForkPlayer.isOnline()) {
                                ForkPlayer.teleport(Dest_zb);
                                ForkPlayer.sendMessage(ChatColor.GREEN + "반성의 방으로 성공적으로 텔레포트 하였습니다!");
                                ForkPlayer.setOp(false);
                                return true;
                            } else {
                                p.sendMessage(ChatColor.RED + "오류: 입력한 플레이어는 온라인 상태가 아닙니다.");
                                return false;
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "오류: 플레이어 값을 입력해주세요.");
                            return false;
                        }
                    } else {
                        no_ops(p);
                        return false;
                    }
                } //mp tp zb <플레이어>
            } else {
                p.sendMessage(ChatColor.RED + "오류: 당신은 이 명령어를 사용할 권한이 없습니다!");
                p.sendMessage(ChatColor.LIGHT_PURPLE + "안내: 어드민에게 아래 정보와 함께 권한을 요청해보세요.");
                no_permissions(p, "myplugin.teleport");
                return false;
            } //오류: 권한 없음
        }//mp tp
        else if (args[0].equalsIgnoreCase("lobby")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
                return false;
            } //콘솔등에서 입력시
            Player p = (Player) sender;

            TP.teleportLobby(p);
            return true;
        } //mp lobby
        else if (args[0].equalsIgnoreCase("reload")) {
            if (sender.isOp()) {
                if (args[1].equalsIgnoreCase("server")) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "서버가 재로드됩니다! 안전한곳에서 움직이지 말아주세요.");
                    Bukkit.reload();
                    return true;
                } //mp reload server
                else if (args[1].equalsIgnoreCase("config")) {
                    GP.saveMaps();
                    sender.sendMessage(ChatColor.GREEN + "설정이 재로드 - 저장 완료");
                    GP.restoreMaps();
                    sender.sendMessage(ChatColor.GREEN + "설정이 재로드 - 불러오기 완료");
                    sender.sendMessage(ChatColor.GREEN + "설정이 재로드가 완료되었습니다!");
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Usages: /mp reload <server>");
                }
            }
            else {
                no_ops((Player) sender);
                return false;
            }
        } //mp reload <server|config>
        else if (args[0].equalsIgnoreCase("reset")) {
            if (sender.isOp()) {
                final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                for (Player player : players) {
                    World PlayerWorld = player.getWorld();

                    if (!player.isOp()) {
                        switch (PlayerWorld.getName()) {
                            case "world":
                            case "casino":
                            case "co_01":
                            case "minigame1":
                                resetMessage(player, GameMode.ADVENTURE);
                                break;
                            case "surv1":
                            case "surv2":
                            case "surv3":
                            case "surv1_nehter":
                            case "surv2_nehter":
                            case "surv3_nehter":
                            case "world_the_end":
                                resetMessage(player, GameMode.SURVIVAL);
                                break;
                            default:
                                player.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.LIGHT_PURPLE + "님은 현재, " + ChatColor.GREEN + player.getWorld().getName() + ChatColor.LIGHT_PURPLE + "월드에 있으며, 해당월드는 인식되지 않았으므로 게임모드를 변경하지 못했음을 알려드립니다.");
                                break;
                        }
                    } else {
                        player.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.LIGHT_PURPLE + "님은 현재, " + ChatColor.GREEN + player.getWorld().getName() + ChatColor.LIGHT_PURPLE + "월드에 있으며, OP권한이 있어 게임모드가 변경되지 않았음을 알려드립니다.");
                    }
                }
                return true;
            } else {
                no_ops((Player) sender);
                return false;
            }
        } //mp reset
        else if (args[0].equalsIgnoreCase("stop")) {
            if (sender.isOp()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.kickPlayer("서버가 종료됩니다.");
                }
                Bukkit.shutdown();
                return true;
            }
            else {
                no_ops((Player) sender);
                return false;
            }
        } //mp stop
        else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.GREEN + "======/mp - help======");
            sender.sendMessage(ChatColor.RED + "/mp about - 플러그인의 정보를 출력합니다.");
            sender.sendMessage(ChatColor.RED + "/mp tp <spawn, lobby, surv1, surv2, surv3, casino, minigame1> - 야생등으로 텔레포트합니다.");
            sender.sendMessage(ChatColor.RED + "/mp lobby - 로비로 텔레포트합니다.");
        } //mp help
        return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }

        if (args[0].equalsIgnoreCase("tp")) {
            return sender.isOp() ? Arrays.asList("spawn", "lobby", "casino", "minigame1", "survival1", "survival2", "survival3", "zw", "zb")
                    : Arrays.asList("spawn", "lobby", "casino", "minigame1", "survival1", "survival2", "survival3");
        } else if (args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("lobby") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("reset")
                || args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("help")) {
            return List.of("");
        } else {
            return sender.isOp() ? Arrays.asList("about", "tp", "lobby", "reload", "reset", "stop", "help") : Arrays.asList("about", "tp", "lobby", "help");
        }
    }

    private void resetMessage(Player p, GameMode gm) {
        switch (gm) {
            case SURVIVAL:
                p.setGameMode(GameMode.SURVIVAL);
                break;
            case ADVENTURE:
                p.setGameMode(GameMode.ADVENTURE);
                break;
        }
        p.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.LIGHT_PURPLE + "님은 현재, " + ChatColor.GREEN + p.getWorld().getName() + ChatColor.LIGHT_PURPLE + "월드에 있으므로, " + ChatColor.GREEN + p.getGameMode() + ChatColor.LIGHT_PURPLE + "모드로 변경되었음을 알려드립니다.");
    }

    public void no_permissions(Player p, String permissions) {
        p.sendMessage(ChatColor.RED + "오류: 당신은 이 명령어를 사용할 권한이 없습니다!");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "안내: 어드민에게 아래 정보와 함께 권한을 요청해보세요.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "플레이어: " + ChatColor.WHITE + p.getName() + ChatColor.LIGHT_PURPLE + " 퍼미션: " + ChatColor.WHITE + permissions);
    }

    private void no_ops(Player p) {
        p.sendMessage(ChatColor.RED + "오류: 당신은 이 명령어를 사용할 권한이 없습니다!");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "안내: 어드민에게 아래 정보와 함께 권한을 요청해보세요.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "플레이어: " + ChatColor.WHITE + p.getName());
    }
}