import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        AVL avl = new AVL();
        BufferedReader br = null;
        Scanner sc = new Scanner(System.in);

        // Lista de todas as chaves inseridas
        List<String> chaves = new ArrayList<String>();

        try {
            br = new BufferedReader(new FileReader("src/main/java/dataset.csv"));
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String municipio = data[0];
                String ano = data[1];
                int obitos = Integer.parseInt(data[2]);
                String key = municipio + "_" + ano;

                Counter counterInsertBST = new Counter();
                Counter counterInsertAVL = new Counter();

                bst.root = bst.insert(bst.root, key, obitos, counterInsertBST);
                avl.root = avl.insert(avl.root, key, obitos, counterInsertAVL);

                chaves.add(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nO que você quer fazer?");
            System.out.println("1 - Inserir");
            System.out.println("2 - Buscar");
            System.out.println("3 - Remover");
            System.out.println("4 - Mostrar todos os dados (in-order e análises)");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();  // consumir quebra de linha

            switch (opcao) {
                case 1: // Inserir
                    System.out.print("Digite o município: ");
                    String municipio = sc.nextLine();

                    System.out.print("Digite o ano: ");
                    String ano = sc.nextLine();

                    System.out.print("Digite o número de óbitos: ");
                    int obitos = sc.nextInt();
                    sc.nextLine();  // consumir quebra de linha

                    String key = municipio + "_" + ano;

                    Counter counterInsertBST = new Counter();
                    Counter counterInsertAVL = new Counter();

                    bst.root = bst.insert(bst.root, key, obitos, counterInsertBST);
                    avl.root = avl.insert(avl.root, key, obitos, counterInsertAVL);

                    chaves.add(key);

                    System.out.println("Inserido com sucesso! Chave: " + key);
                    System.out.println("Comparações realizadas:");
                    System.out.println("BST → " + counterInsertBST.count + " comparações");
                    System.out.println("AVL → " + counterInsertAVL.count + " comparações");
                    break;

                case 2: // Buscar
                    if (chaves.isEmpty()) {
                        System.out.println("Nenhum dado para buscar.");
                        break;
                    }

                    System.out.println("Itens disponíveis para busca:");
                    for (String c : chaves) {
                        System.out.println("- " + c);
                    }

                    System.out.print("Escolha uma chave: ");
                    String busca = sc.nextLine();

                    Counter counterSearchBST = new Counter();
                    Counter counterSearchAVL = new Counter();

                    Node resultBST = bst.search(bst.root, busca, counterSearchBST);
                    Node resultAVL = avl.search(avl.root, busca, counterSearchAVL);

                    if (resultBST != null)
                        System.out.println("BST: Encontrado - " + resultBST.value + " óbitos.");
                    else
                        System.out.println("BST: Não encontrado.");

                    if (resultAVL != null)
                        System.out.println("AVL: Encontrado - " + resultAVL.value + " óbitos.");
                    else
                        System.out.println("AVL: Não encontrado.");

                    System.out.println("Comparações realizadas:");
                    System.out.println("BST → " + counterSearchBST.count + " comparações");
                    System.out.println("AVL → " + counterSearchAVL.count + " comparações");
                    break;

                case 3: // Remover
                    if (chaves.isEmpty()) {
                        System.out.println("Nenhum dado para remover.");
                        break;
                    }

                    System.out.println("Itens disponíveis para remoção:");
                    for (String c : chaves) {
                        System.out.println("- " + c);
                    }

                    System.out.print("Escolha uma chave para remover: ");
                    String remocao = sc.nextLine();

                    Counter counterDeleteBST = new Counter();
                    Counter counterDeleteAVL = new Counter();

                    bst.root = bst.delete(bst.root, remocao, counterDeleteBST);
                    avl.root = avl.delete(avl.root, remocao, counterDeleteAVL);

                    chaves.remove(remocao);

                    System.out.println("Remoção concluída.");
                    System.out.println("Comparações realizadas:");
                    System.out.println("BST → " + counterDeleteBST.count + " comparações");
                    System.out.println("AVL → " + counterDeleteAVL.count + " comparações");
                    break;

                case 4:
                    System.out.println("\nTotal de casos na BST: " + Analysis.totalCasos(bst.root));

                    Node maxNode = Analysis.findMax(bst.root);
                    if (maxNode != null) {
                        System.out.println("Maior registro: " + maxNode.key + " - " + maxNode.value);
                    } else {
                        System.out.println("Árvore vazia.");
                    }

                    System.out.print("\nDigite o ano mínimo para análise(2019 - 2023): ");
                    int anoMin = sc.nextInt();

                    System.out.print("Digite o ano máximo para análise(2019 - 2023): ");
                    int anoMax = sc.nextInt();
                    sc.nextLine();

                    System.out.println("\nÓbitos entre " + anoMin + " e " + anoMax + ":");
                    Analysis.printInRange(bst.root, anoMin, anoMax);

                    System.out.println("\nImpressão completa em ordem:");
                    Analysis.printInOrder(bst.root);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}
