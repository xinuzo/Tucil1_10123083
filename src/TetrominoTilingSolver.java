import java.util.*;
import java.io.*;

public class TetrominoTilingSolver {

    public static class Point implements Comparable<Point> {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point other) {
            if (this.x != other.x)
                return this.x - other.x;
            return this.y - other.y;
        }
    }

    public static List<Point> rotate(List<Point> shape) {
        List<Point> newShape = new ArrayList<>();
        for (Point p : shape) {
            newShape.add(new Point(p.y, -p.x));
        }
        return newShape;
    }

    public static List<Point> reflect(List<Point> shape) {
        List<Point> newShape = new ArrayList<>();
        for (Point p : shape) {
            newShape.add(new Point(p.x, -p.y));
        }
        return newShape;
    }

    public static List<Point> normalize(List<Point> shape) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        for (Point p : shape) {
            if (p.x < minX) minX = p.x;
            if (p.y < minY) minY = p.y;
        }
        List<Point> normalized = new ArrayList<>();
        for (Point p : shape) {
            normalized.add(new Point(p.x - minX, p.y - minY));
        }
        Collections.sort(normalized);
        return normalized;
    }

    public static List<List<Point>> generateTransformations(List<Point> original) {
        TreeSet<List<Point>> uniqueShapes = new TreeSet<>(new Comparator<List<Point>>() {
            @Override
            public int compare(List<Point> shape1, List<Point> shape2) {
                if (shape1.size() != shape2.size())
                    return shape1.size() - shape2.size();
                for (int i = 0; i < shape1.size(); i++) {
                    int cmp = shape1.get(i).compareTo(shape2.get(i));
                    if (cmp != 0)
                        return cmp;
                }
                return 0;
            }
        });

        List<Point> current = new ArrayList<>(original);
        for (int i = 0; i < 4; i++) {
            List<Point> normalized = normalize(current);
            uniqueShapes.add(normalized);

            List<Point> reflected = normalize(reflect(current));
            uniqueShapes.add(reflected);

            current = rotate(current);
        }
        return new ArrayList<>(uniqueShapes);
    }

    public static class Piece {
        List<Point> shape;
        char letter;
        public Piece(List<Point> shape, char letter) {
            this.shape = shape;
            this.letter = letter;
        }
    }

    public static Piece parsePiece(List<String> pieceLines) {
        List<Point> shape = new ArrayList<>();
        Character letter = null;
        for (int i = 0; i < pieceLines.size(); i++) {
            String line = pieceLines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);
                if (ch != ' ') {
                    shape.add(new Point(i, j));
                    if (letter == null)
                        letter = ch;
                }
            }
        }
        return new Piece(shape, letter == null ? '?' : letter);
    }

    public static class Solver {
        int M, N;
        boolean[][] grid;
        int attempts = 0;
        boolean solutionFound = false;
        char[][] solutionGrid;
        List<List<List<Point>>> piecesTransformations = new ArrayList<>();
        List<Character> pieceLetters = new ArrayList<>();
        long duration;

        public Solver(int M, int N, List<List<String>> piecesInput) {
            this.M = M;
            this.N = N;
            grid = new boolean[M][N];
            solutionGrid = new char[M][N];
            for (int i = 0; i < M; i++) {
                Arrays.fill(solutionGrid[i], '.');
            }

            for (List<String> pieceLines : piecesInput) {
                Piece piece = parsePiece(pieceLines);
                List<List<Point>> transformations = generateTransformations(piece.shape);
                piecesTransformations.add(transformations);
                pieceLetters.add(piece.letter);
            }
        }

        public boolean canPlace(List<Point> shape, int x, int y) {
            for (Point p : shape) {
                int nx = x + p.x;
                int ny = y + p.y;
                if (nx < 0 || nx >= M || ny < 0 || ny >= N || grid[nx][ny])
                    return false;
            }
            return true;
        }

        public void place(List<Point> shape, int x, int y, char letter) {
            for (Point p : shape) {
                grid[x + p.x][y + p.y] = true;
                solutionGrid[x + p.x][y + p.y] = letter;
            }
        }

        public void remove(List<Point> shape, int x, int y) {
            for (Point p : shape) {
                grid[x + p.x][y + p.y] = false;
                solutionGrid[x + p.x][y + p.y] = '.';
            }
        }

        public boolean gridFilled() {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (!grid[i][j])
                        return false;
                }
            }
            return true;
        }

        public void backtrack(int pieceIdx) {
            if (pieceIdx == piecesTransformations.size()) {
                if (gridFilled()) {
                    solutionFound = true;
                }
                return;
            }
            List<List<Point>> transformations = piecesTransformations.get(pieceIdx);
            for (List<Point> shape : transformations) {
                for (int x = 0; x < M; x++) {
                    for (int y = 0; y < N; y++) {
                        attempts++;
                        if (canPlace(shape, x, y)) {
                            place(shape, x, y, pieceLetters.get(pieceIdx));
                            backtrack(pieceIdx + 1);
                            if (solutionFound)
                                return;
                            remove(shape, x, y);
                        }
                    }
                }
            }
        }

        public void solve() {
            long start = System.currentTimeMillis();
            backtrack(0);
            long end = System.currentTimeMillis();
            duration = end - start;
        }

        public boolean isSolutionFound() {
            return solutionFound;
        }

        public char[][] getSolutionGrid() {
            return solutionGrid;
        }

        public long getDuration() {
            return duration;
        }

        public int getAttempts() {
            return attempts;
        }
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Masukkan nama file: ");
        String fileName = inputScanner.nextLine();
        File inputFile = new File(fileName);
        List<String> colors = Arrays.asList(
            "\033[38;2;255;0;0m",     // Red
            "\033[38;2;0;255;0m",     // Green
            "\033[38;2;0;0;255m",     // Blue
            "\033[38;2;255;255;0m",   // Yellow
            "\033[38;2;255;165;0m",   // Orange
            "\033[38;2;128;0;128m",   // Purple
            "\033[38;2;0;255;255m",   // Cyan
            "\033[38;2;255;192;203m", // Pink
            "\033[38;2;165;42;42m",   // Brown
            "\033[38;2;75;0;130m",    // Indigo
            "\033[38;2;255;215;0m",   // Gold
            "\033[38;2;173;216;230m", // Light Blue
            "\033[38;2;0;128;0m",     // Dark Green
            "\033[38;2;128;128;128m", // Gray
            "\033[38;2;192;192;192m", // Silver
            "\033[38;2;0;0;128m",     // Navy
            "\033[38;2;128;0;0m",     // Maroon
            "\033[38;2;0;128;128m",   // Teal
            "\033[38;2;139;69;19m",   // Saddle Brown
            "\033[38;2;46;139;87m",   // Sea Green
            "\033[38;2;255;99;71m",   // Tomato
            "\033[38;2;186;85;211m",  // Medium Orchid
            "\033[38;2;30;144;255m",  // Dodger Blue
            "\033[38;2;154;205;50m",  // Yellow Green
            "\033[38;2;218;112;214m", // Orchid
            "\033[38;2;107;142;35m"   // Olive Drab
    );
        try (Scanner sc = new Scanner(inputFile)) {
            int M = sc.nextInt();
            int N = sc.nextInt();
            int P = sc.nextInt();
            sc.nextLine();

            sc.nextLine();

            List<String> remainingLines = new ArrayList<>();
            while (sc.hasNextLine()) {
                remainingLines.add(sc.nextLine());
            }

            List<List<String>> pieces = new ArrayList<>();
            int index = 0;
            for (int i = 0; i < P; i++) {
                List<String> pieceLines = new ArrayList<>();
                while (index < remainingLines.size() && remainingLines.get(index).trim().isEmpty()) {
                    index++;
                }
                if (index >= remainingLines.size()) {
                    break;
                }
                String firstLine = remainingLines.get(index);
                String trimmedFirst = firstLine.trim();
                if (trimmedFirst.isEmpty()) {
                    index++;
                    continue;
                }
                char pieceChar = trimmedFirst.charAt(0);
                pieceLines.add(firstLine);
                index++;
                while (index < remainingLines.size()) {
                    String nextLine = remainingLines.get(index);
                    String trimmedNext = nextLine.trim();
                    if (trimmedNext.isEmpty()) {
                        index++;
                        continue;
                    }
                    if (trimmedNext.charAt(0) == pieceChar) {
                        pieceLines.add(nextLine);
                        index++;
                    } else {
                        break;
                    }
                }
                pieces.add(pieceLines);
            }System.out.println();

            Solver solver = new Solver(M, N, pieces);
            solver.solve();
            if (solver.isSolutionFound()) {
                char[][] solutionGrid = solver.getSolutionGrid();
                Set<Character> uniqueLetters = new HashSet<>();
                for (char[] row : solutionGrid) {
                    for (char c : row) {
                        if (c != '.' && !uniqueLetters.contains(c)) {
                            uniqueLetters.add(c);
                        }
                    }
                }
                List<Character> letters = new ArrayList<>(uniqueLetters);
                List<String> shuffledColors = new ArrayList<>(colors);
                Collections.shuffle(shuffledColors);
                
                while (shuffledColors.size() < letters.size()) {
                    Collections.shuffle(colors);
                    shuffledColors.addAll(colors);
                }

                Map<Character, String> colorMap = new HashMap<>();
                for (int i = 0; i < letters.size(); i++) {
                    colorMap.put(letters.get(i), shuffledColors.get(i));
                }
                
                String reset = "\033[0m";
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < N; j++) {
                        char c = solutionGrid[i][j];
                        String color = colorMap.getOrDefault(c, reset);
                        System.out.print(color + c + reset + " ");
                    }
                    System.out.println();
                }
                
            System.out.println("Waktu pencarian: " + solver.getDuration() + " ms");
            System.out.println("Banyak kasus yang ditinjau: " + solver.getAttempts());

                System.out.println("Simpan solusi ke file? (yes/no)");
                String choice = inputScanner.nextLine().trim().toLowerCase();
                if (choice.equals("yes") || choice.equals("y")) {
                    System.out.println("Masukkan nama file: ");
                    String outFile = inputScanner.nextLine();
                    try (PrintWriter writer = new PrintWriter(outFile)) {
                        for (int i = 0; i < M; i++) {
                            for (int j = 0; j < N; j++) {
                                writer.print(solutionGrid[i][j]);
                            }
                            writer.println();
                        }
                        System.out.println("Solusi disimpan di " + outFile);

                    } catch (IOException e) {
                        System.out.println("Gagal menyimpan: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("Tidak ada solusi yang ditemukan.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } finally {
            inputScanner.close();
        }
    }
}
