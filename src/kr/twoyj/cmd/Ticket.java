package kr.twoyj.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class Ticket implements CommandExecutor, TabCompleter {
    public static Map<UUID, Integer> ticket = new HashMap<>();
    private List<String> players = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "오류: 사용법이 잘못되었습니다. /tk help를 참고하세요.");
        } //사용방법 출력
        else if (args[0].equalsIgnoreCase("add")) {
            try {
                if (sender.isOp()) {
                    if (args[1].isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "오류: 티켓을 지급할 플레이어가 입력되지 않았습니다.");
                    } else {
                        if (args[2].isEmpty()) {
                            sender.sendMessage(ChatColor.RED + "오류: 지급할 티켓 수량이 입력되지 않았습니다.");
                        } else {
                            if (!ticket.containsKey(getUUID(args[1]))) {
                                sender.sendMessage(ChatColor.RED + "오류: 입력한 플레이어가 서버에 접속한 기록이 없습니다.");
                            } else {
                                ticket.put(getUUID(args[1]), ticket.get(getUUID(args[1])) + Integer.parseInt(args[2]));
                                sender.sendMessage(Bukkit.getOfflinePlayer(args[1]).getName() + ChatColor.GREEN + "님에게 티켓 " + ChatColor.WHITE + args[2] + ChatColor.GREEN + "장을 추가하였습니다.");
                                if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                                    Bukkit.getPlayer(args[1]).sendMessage(sender.getName() + ChatColor.GREEN + "님에 의하여 티켓 " + ChatColor.WHITE + args[2] + ChatColor.GREEN + "장이 추가되었습니다.");
                                }
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "오류: 이 기능은 OP 권한만 사용가능합니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED + "Usages: /tk add <player> <amount>");
            }
            return false;
        } //tk add <플레이어> <수량>
        else if (args[0].equalsIgnoreCase("set")) {
            try {
                if (sender.isOp()) {
                    if (args[1].isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "오류: 티켓을 설정할 플레이어가 입력되지 않았습니다.");
                    } else {
                        if (args[2].isEmpty()) {
                            sender.sendMessage(ChatColor.RED + "오류: 설정할 티켓 수량이 입력되지 않았습니다.");
                        } else {
                            if (!ticket.containsKey(getUUID(args[1]))) {
                                sender.sendMessage(ChatColor.RED + "오류: 입력한 플레이어가 서버에 접속한 기록이 없습니다.");
                            } else {
                                ticket.put(getUUID(args[1]), Integer.parseInt(args[2]));
                                sender.sendMessage(Bukkit.getOfflinePlayer(args[1]).getName() + ChatColor.GREEN + "님의 티켓을 " + ChatColor.WHITE + args[2] + ChatColor.GREEN + "장으로 설정하였습니다.");
                                if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                                    Bukkit.getPlayer(args[1]).sendMessage(sender.getName() + ChatColor.GREEN + "님에 의하여 티켓 " + ChatColor.WHITE + args[2] + ChatColor.GREEN + "장으로 설정되었습니다.");
                                }
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "오류: 이 기능은 OP 권한만 사용가능합니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED + "Usages: /tk set <player> <amount>");
            }
            return false;
        } //tk set <플레이어> <수량>
        else if (args[0].equalsIgnoreCase("remove")) {
            try {
                if (sender.isOp()) {
                    if (args[1].isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "오류: 티켓을 삭제할 플레이어가 입력되지 않았습니다.");
                    } else {
                        if (args[2].isEmpty()) {
                            sender.sendMessage(ChatColor.RED + "오류: 삭제할 티켓 수량이 입력되지 않았습니다.");
                        } else {
                            if (!ticket.containsKey(getUUID(args[1]))) {
                                sender.sendMessage(ChatColor.RED + "오류: 입력한 플레이어가 서버에 접속한 기록이 없습니다.");
                            } else {
                                ticket.put(getUUID(args[1]), ticket.get(getUUID(args[1])) - Integer.parseInt(args[2]));
                                sender.sendMessage(Bukkit.getOfflinePlayer(args[1]).getName() + ChatColor.GREEN + "님에게 티켓 " + ChatColor.WHITE + args[2] + ChatColor.GREEN + "장을 제거하였습니다.");
                                if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                                    Bukkit.getPlayer(args[1]).sendMessage(sender.getName() + ChatColor.GREEN + "님에 의하여 티켓 " + ChatColor.WHITE + args[2] + ChatColor.GREEN + "장이 제거되었습니다.");
                                }
                                if (ticket.get(getUUID(args[1])) < 0) {
                                    ticket.put(getUUID(args[1]), 0);
                                }
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "오류: 이 기능은 OP 권한만 사용가능합니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED + "Usages: /tk remove <player> <amount>");
            }
            return false;
        } //tk remove <플레이어> <수량>
        else if (args[0].equalsIgnoreCase("clear")) {
            try {
                if (sender.isOp()) {
                    if (args[1].isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "오류: 티켓을 초기화할 플레이어가 입력되지 않았습니다.");
                    } else {
                        if (!ticket.containsKey(getUUID(args[1]))) {
                            sender.sendMessage(ChatColor.RED + "오류: 입력한 플레이어가 서버에 접속한 기록이 없습니다.");
                        } else {
                            ticket.put(getUUID(args[1]), 0);
                            sender.sendMessage(Bukkit.getOfflinePlayer(args[1]).getName() + ChatColor.GREEN + "님의 티켓을 초기화하였습니다.");
                            if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                                Bukkit.getPlayer(args[1]).sendMessage(sender.getName() + ChatColor.GREEN + "님에 의하여 티켓이 초기화 되었습니다.");
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "오류: 이 기능은 OP 권한만 사용가능합니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED + "Usages: /tk clear <player>");
            }
        } //tk clear <플레이어>
        else if (args[0].equalsIgnoreCase("value")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
                return false;
            } //콘솔등에서 입력시
            Player p = (Player) sender;
            p.sendMessage(p.getName() + ChatColor.GREEN + "님에게 티켓이 " + ChatColor.WHITE + ticket.get(p.getUniqueId()) + ChatColor.GREEN + "장 있습니다.");
        } //tk value
        else if (args[0].equalsIgnoreCase("gift")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
                return false;
            } //콘솔등에서 입력시
            Player p = (Player) sender;
            try {
                if (args[1].isEmpty()) {
                    p.sendMessage(ChatColor.RED + "오류: 티켓을 선물할 플레이어가 입력되지 않았습니다.");
                } else {
                    if (args[2].isEmpty()) {
                        p.sendMessage(ChatColor.RED + "오류: 티켓을 선물할 수량이 입력되지 않았습니다.");
                    } else {
                        if (ticket.containsKey(getUUID(args[1]))) {
                            if (ticket.get(p.getUniqueId()) < Integer.parseInt(args[2])) {
                                p.sendMessage(ChatColor.RED + "오류: 선물하려고 하는 티켓의 수량이 현재 보유하고 있는 티켓의 수량보다 많습니다.");
                            } else {
                                ticket.put(getUUID(args[1]), ticket.get(getUUID(args[1])) + Integer.parseInt(args[2]));
                                ticket.put(p.getUniqueId(), ticket.get(p.getUniqueId()) - Integer.parseInt(args[2]));
                                p.sendMessage(args[1] + ChatColor.GREEN + "님에게 티켓 " + ChatColor.WHITE + args[2] + ChatColor.GREEN + "장을 선물했습니다.");
                                if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                                    Bukkit.getPlayer(args[1]).sendMessage(p.getName() + ChatColor.GREEN + "님에게서 티켓 " + ChatColor.WHITE + args[2] + ChatColor.GREEN + "장을 선물 받았습니다.");
                                }
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "오류: 입력한 플레이어가 서버에 접속한 기록이 없습니다.");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                p.sendMessage(ChatColor.RED + "Usages: /tk gift <player> <amount>");
            }
            return false;
        } //tk gift <플레이어> <수량>
        else if (args[0].equalsIgnoreCase("buy")) {
            try {
                if (args[1].isEmpty()) {
                    sender.sendMessage(ChatColor.RED + "오류: 티켓을 구매할 수단이 입력되지 않았습니다.");
                } else {
                    if (args[2].isEmpty()) {
                        sender.sendMessage(ChatColor.RED + "오류: 티켓을 구매할 수량이 입력되지 않았습니다.");
                    } else {
                        double i = (Integer.parseInt(args[2])) / 10;
                        int j = (int) Math.floor(i);
                        if (args[1].equalsIgnoreCase("1")) {
                            sender.sendMessage(ChatColor.GREEN + "선택된 수단: 계좌이체 | 수수료: 0%");
                            sender.sendMessage(ChatColor.GREEN + "계좌이체로 티켓 " + args[2] + "장을 구매하시면 " + (Integer.parseInt(args[2]) * 500) + "원으로 구매하실수 있으며 티켓 " + j + "장이 추가로 지급됩니다.");
                            sender.sendMessage(ChatColor.GREEN + "구매하시려면 어드민에게 문의 바랍니다.");
                        } else if (args[1].equalsIgnoreCase("2")) {
                            sender.sendMessage(ChatColor.GREEN + "선택된 수단: 문화상품권 | 수수료: 10%");
                            sender.sendMessage(ChatColor.GREEN + "문화상품권으로 티켓 " + args[2] + "장을 구매하시면 " + (Integer.parseInt(args[2]) * 550) + "원으로 구매하실수 있으며 티켓 " + j + "장이 추가로 지급됩니다.");
                            sender.sendMessage(ChatColor.GREEN + "참고) 문화상품권 1천원권으로 티켓 1장 구매시(수수료 포함 550원) -> 잔액 450원은 반환하지 않습니다.");
                            sender.sendMessage(ChatColor.GREEN + "구매하시려면 어드민에게 문의 바랍니다.");
                        } else if (args[1].equalsIgnoreCase("3")) {
                            sender.sendMessage(ChatColor.GREEN + "선택된 수단: 해피머니 | 수수료: 10%");
                            sender.sendMessage(ChatColor.GREEN + "해피머니로 티켓 " + args[2] + "장을 구매하시면 " + (Integer.parseInt(args[2]) * 550) + "원으로 구매하실수 있으며 티켓 " + j + "장이 추가로 지급됩니다.");
                            sender.sendMessage(ChatColor.GREEN + "참고) 해피머니 1천원권으로 티켓 1장 구매시(수수료 포함 550원) -> 잔액 450원은 반환하지 않습니다.");
                            sender.sendMessage(ChatColor.GREEN + "구매하시려면 어드민에게 문의 바랍니다.");
                        } else if (args[1].equalsIgnoreCase("4")) {
                            sender.sendMessage(ChatColor.GREEN + "선택된 수단: 컬쳐캐시교환권 | 수수료: 10%");
                            sender.sendMessage(ChatColor.GREEN + "컬쳐캐시교환권으로 티켓 " + args[2] + "장을 구매하시면 " + (Integer.parseInt(args[2]) * 550) + "원으로 구매하실수 있으며 티켓 " + j + "장이 추가로 지급됩니다.");
                            sender.sendMessage(ChatColor.GREEN + "참고) 컬쳐캐시교환권 1천원권으로 티켓 1장 구매시(수수료 포함 550원) -> 잔액 450원은 반환하지 않습니다.");
                            sender.sendMessage(ChatColor.GREEN + "구매하시려면 어드민에게 문의 바랍니다.");
                        } else if (args[1].equalsIgnoreCase("5")) {
                            sender.sendMessage(ChatColor.GREEN + "선택된 수단: 틴캐시 | 수수료: 10%");
                            sender.sendMessage(ChatColor.GREEN + "틴캐시로 티켓 " + args[2] + "장을 구매하시면 " + (Integer.parseInt(args[2]) * 550) + "원으로 구매하실수 있으며 티켓 " + j + "장이 추가로 지급됩니다.");
                            sender.sendMessage(ChatColor.GREEN + "참고) 틴캐시 1천원권으로 티켓 1장 구매시(수수료 포함 550원) -> 잔액 450원은 반환하지 않습니다.");
                            sender.sendMessage(ChatColor.GREEN + "구매하시려면 어드민에게 문의 바랍니다.");
                        } else if (args[1].equalsIgnoreCase("6")) {
                            sender.sendMessage(ChatColor.GREEN + "선택된 수단: 구글 기프트카드 | 수수료: 25%");
                            sender.sendMessage(ChatColor.GREEN + "구글 기프트카드로 티켓 " + args[2] + "장을 구매하시면 " + (Integer.parseInt(args[2]) * 625) + "원으로 구매하실수 있으며 티켓 " + j + "장이 추가로 지급됩니다.");
                            sender.sendMessage(ChatColor.GREEN + "참고) 구글 기프트카드 1천원권으로 티켓 1장 구매시(수수료 포함 625원) -> 잔액 375원은 반환하지 않습니다.");
                            sender.sendMessage(ChatColor.GREEN + "구매하시려면 어드민에게 문의 바랍니다.");
                        } else if (args[1].equalsIgnoreCase("7")) {
                            sender.sendMessage(ChatColor.GREEN + "선택된 수단: 다이아몬드 | 수수료: 0%");
                            sender.sendMessage(ChatColor.GREEN + "다이아몬드로 티켓 " + args[2] + "장을 구매하시면 " + (Integer.parseInt(args[2]) * 2) + "개로 구매하실수 있으며 티켓 " + j + "장이 추가로 지급됩니다.");
                            sender.sendMessage(ChatColor.GREEN + "구매하시려면 어드민에게 문의 바랍니다.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "오류: 티켓을 구매할 수단이 올바르지 않습니다. (계좌이체=1|문화상품권=2|해피머니=3|컬쳐랜드교환권=4|틴캐시=5|구글기프트카드=6|다이아몬드=7)");
                        }
                    }
                }
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Usages: /tk buy <계좌이체=1|문화상품권=2|해피머니=3|컬쳐랜드교환권=4|틴캐시=5|구글기프트카드=6|다이아몬드=7> <amount>");
            }
        } //tk buy <수단> <수량>
        else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.GREEN + "======/ticket - help======");
            sender.sendMessage(ChatColor.RED + "/tk value - 자신의 티켓 수량을 확인합니다.");
            sender.sendMessage(ChatColor.RED + "/tk gift <플레이어> <수량> - 자신의 티켓을 <수량> 만큼 <플레이어>에게 선물합니다.");
            sender.sendMessage(ChatColor.RED + "/tk buy <계좌이체=1|문화상품권=2|해피머니=3|컬쳐랜드교환권=4|틴캐시=5|구글기프트카드=6|다이아몬드=7> <수량> - 티켓을 <수량> 만큼 구매합니다.");
            sender.sendMessage(ChatColor.RED + "*티켓 1장당 다이아몬드 2개/500원  |  10장 구매시 1장씩 추가 증정");
        } //tk help
        return false;
    }

    private UUID getUUID(String str) {
        UUID getUUID = Bukkit.getOfflinePlayer(str).getUniqueId();
        return getUUID;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }

        if (args[0].equalsIgnoreCase("buy")) {
            switch(args[1]) {
                case "1", "2", "3", "4", "5", "6", "7" -> { return List.of(""); }
            }
            return Arrays.asList("1", "2", "3", "4", "5", "6", "7");
        } else if (args[0].equalsIgnoreCase("value") || args[0].equalsIgnoreCase("help")) {
            return List.of("");
        } else {
            return sender.isOp() ? Arrays.asList("add", "set", "remove", "clear", "value", "gift", "buy", "help") : Arrays.asList("value", "gift", "buy", "help");
        }
    }
}