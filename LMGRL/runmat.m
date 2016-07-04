function  runmat(a1, a2, a3, a4, a5, a6, a7, a8 ,targetXlength)
data={a1, a2, a3, a4, a5, a6, a7, a8};
targetX = [1:targetXlength];
multiRelation_cell={[1:4] [5:7]};
alpha=[1 0.7 0.5];
for j=1: length(alpha)
    disp(strcat('**********Alpha is #',int2str(alpha(j))))
    for i=1:length(data)
        disp(strcat('########File num is #',int2str(i)))
        LlowApproxmationResult = MatLMGRS(data{i}, multiRelation_cell, targetX, alpha(j));
       % GlowApproxmationResult = MatGMGRS(data{i}, multiRelation_cell, targetX, alpha(j));
    end
end

end